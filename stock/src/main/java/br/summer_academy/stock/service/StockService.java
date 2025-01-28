package br.summer_academy.stock.service;

import java.util.ArrayList;
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
    private String exchange_direct;

    @Value("${rabbitmq.routing.stock.to.payment}")
    private String key_stock_to_payment;

    @Value("${rabbitmq.routing.stock.to.status}")
    private String key_stock_to_status;

    public StockService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public boolean checkIfAvailable(List<Product> products) {
        if (products.contains(null)) return false;
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
        List<Product> products = new ArrayList<>();
    
        System.out.println("\n");
        for (ProductRecordDTO dto : productDTOs) {
            Product stockProduct = repository.findByName(dto.name());
    
            if (stockProduct == null) {
                System.out.print("Product with name '" + dto.name() + "' not found.");
                products.add(null); // Insert null for missing product
                continue; // Skip this product instead of returning null
            }
    
            Product product = new Product();
            product.setId(stockProduct.getId());
            product.setName(dto.name());
            product.setQuantity(dto.quantity());
            product.setPrice(stockProduct.getPrice());
            products.add(product);
        }
        return products;
    }    

    // Approve and send to Payment
    public void approveOrderAndValue(OrderRecordDTO order) {
        StockOrderDTO dto = new StockOrderDTO();
        dto.setOrder_id(order.id());
        dto.setClient_id(order.client().id());
        dto.setApproval(true);
        rabbitTemplate.convertAndSend(exchange_direct, key_stock_to_payment, dto);
        System.out.println("Status: Approved");
    }
    
    // Disapprove and send to Payment
    public void disapproveOrderAndValue(OrderRecordDTO order) {
        StockOrderDTO dto = new StockOrderDTO();
        dto.setOrder_id(order.id());
        dto.setClient_id(order.client().id());
        dto.setApproval(false);
        rabbitTemplate.convertAndSend(exchange_direct, key_stock_to_payment, dto);
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

    public void sendOrderToStatus(OrderRecordDTO dto) {
        rabbitTemplate.convertAndSend(exchange_direct, key_stock_to_status, dto);
        System.out.println("Order sent to Status.");
    }

}
