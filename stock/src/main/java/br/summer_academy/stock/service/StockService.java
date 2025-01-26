package br.summer_academy.stock.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.summer_academy.stock.dto.OrderRecordDTO;
import br.summer_academy.stock.dto.ProductRecordDTO;
import br.summer_academy.stock.dto.StockOrderDTO;
import br.summer_academy.stock.model.Product;
import br.summer_academy.stock.repository.StockRepository;

@Service
public class StockService {
    @Autowired
    StockRepository repository;

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.direct}")
    private String directExchange;

    @Value("${rabbitmq.routing.stock.to.payment}")
    private String paymentStockRoutingKey;

    public StockService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public boolean checkIfAvailable(List<Product> products) {
        for (Product p : products) {
            Product stockProduct = repository.findByName(p.getName()); // Search by name
            if (stockProduct == null || stockProduct.getQuantity() < p.getQuantity()) { // Need exists in stock and have necessary amount
                return false;
            }
        }
        return true;
    }

    // Mapping DTO to Entity
    public List<Product> mapProducts(List<ProductRecordDTO> productDTOs) {
        return productDTOs.stream().map(dto -> {
            Product stockProduct = repository.findByName(dto.name());
            
            if (stockProduct == null) {
                throw new IllegalArgumentException("Product with name '" + dto.name() + "' not found.");
            }
    
            Product product = new Product();
            product.setId(stockProduct.getId());
            product.setName(dto.name());
            product.setQuantity(dto.quantity());
            product.setPrice(stockProduct.getPrice());
            return product;
        }).collect(Collectors.toList());
    }

    public void approveOrderAndValue(OrderRecordDTO order) {
        BigDecimal totalValue = new BigDecimal(0);
        for (Product p : mapProducts(order.products())) {
            Product stockProduct = repository.findByName(p.getName()); // Search by name
            BigDecimal quantity = BigDecimal.valueOf(p.getQuantity());
            totalValue = totalValue.add(stockProduct.getPrice().multiply(quantity));
        }
        StockOrderDTO dto = new StockOrderDTO();
        dto.setOrder_id(order.id());
        dto.setClient_id(order.client().id());
        dto.setApproval(true);
        dto.setTotalValue(totalValue);
        System.out.println("Total value: " + totalValue);
        rabbitTemplate.convertAndSend(directExchange, paymentStockRoutingKey, dto);
        System.out.println("Status: Approved");
    }
    
    public void disapproveOrderAndValue(OrderRecordDTO order) {
        StockOrderDTO dto = new StockOrderDTO();
        dto.setOrder_id(order.id());
        dto.setClient_id(order.client().id());
        dto.setApproval(false);
        dto.setTotalValue(null);
        rabbitTemplate.convertAndSend(directExchange, paymentStockRoutingKey, dto);
        System.out.println("Status: Not approved");
    }

    public void printProductList(List<Product> products) {
        System.out.println("Product List:");
        for (Product product : products) {
            System.out.println("- " + product);
        }
    }

    public void removeFromStock(List<Product> productsToRemove) {
        for (Product p : productsToRemove) {
            Product updatedStockProduct = repository.findByName(p.getName()); // Search by name
            int updatedQuantity = updatedStockProduct.getQuantity() - p.getQuantity();
            updatedStockProduct.setQuantity(updatedQuantity);
            repository.save(updatedStockProduct); // Need to mach UUID
        }
    }

}
