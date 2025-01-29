package br.summer_academy.stock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import br.summer_academy.stock.dto.OrderRecordDTO;
import br.summer_academy.stock.dto.ProductRecordDTO;
import br.summer_academy.stock.dto.StockOrderDTO;
import br.summer_academy.stock.model.BadProduct;
import br.summer_academy.stock.model.Order;
import br.summer_academy.stock.model.Product;
import br.summer_academy.stock.repository.StockRepository;

@Service
public class StockService {
    @Autowired
    StockRepository repository;

    private List<Product> listOfProducts = new ArrayList<>();
    private List<BadProduct> listOfBadProducts = new ArrayList<>();
    
    @Autowired
    private JavaMailSender mailSender;
    
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.direct}")
    private String exchange_direct;

    @Value("${rabbitmq.routing.stock.to.payment}")
    private String key_stock_to_payment;

    @Value("${rabbitmq.routing.stock.to.status}")
    private String key_stock_to_status;

    @Value("{$spring.mail.username}")
    private String emailFrom;

    public StockService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // Mapping DTO to Entity
    public List<Product> mapProducts(List<ProductRecordDTO> productDTOs) {
        System.out.println("");
        for (ProductRecordDTO dto : productDTOs) {
            Product stockProduct = repository.findByName(dto.name());
    
            if (stockProduct == null) {
                System.out.print("Product with name '" + dto.name() + "' not found.");
                listOfProducts.add(null); // Insert null for missing product

                BadProduct badProduct = new BadProduct();
                badProduct.setName(dto.name());
                badProduct.setQuantity(dto.quantity());
                listOfBadProducts.add(badProduct);
                continue; // Skip this product instead of returning null
            }
    
            Product product = new Product();
            product.setId(stockProduct.getId());
            product.setName(dto.name());
            product.setQuantity(dto.quantity());
            product.setPrice(stockProduct.getPrice());
            listOfProducts.add(product);
        }
        return listOfProducts;
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

    // Approve and send to Payment
    public void approveOrderAndValue(OrderRecordDTO order) {
        StockOrderDTO dto = new StockOrderDTO();
        dto.setOrderId(order.id());
        dto.setProducts(listOfProducts);
        dto.setApproval(true);
        rabbitTemplate.convertAndSend(exchange_direct, key_stock_to_payment, dto);
        listOfProducts.clear();
        System.out.println("Status: Approved");
    }
    
    // Disapprove and send to Payment
    public void disapproveOrderAndValue(OrderRecordDTO order) {
        StockOrderDTO dto = new StockOrderDTO();
        dto.setOrderId(order.id());
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

    public void sendBadEmail(Order orderToEmail) {
        try {
            System.out.println("Sending email to " + orderToEmail.getClient().getEmail());

            var message = new SimpleMailMessage();
            message.setTo(orderToEmail.getClient().getEmail());
            message.setFrom(emailFrom);
            message.setSubject("Pedido NÃO foi aprovado.");    
            message.setText("Olá " + orderToEmail.getClient().getName() + "\n\nSeu pedido foi recebido pelo estoque mas não foi aprovado porque alguns produtos não estão disponíveis ou excedem a quatidade em estoque :(\n\n");
            for (BadProduct badProduct : listOfBadProducts) {
                message.setText(message.getText() + "Produto não aprovado: " + badProduct.getName() + "\n" + "Quantidade: " + badProduct.getQuantity() + "\n\n");
            }
            message.setText(message.getText() + "Atualize seu pedido e tente comprar novamente.");
            mailSender.send(message);
        } catch (MailException e) {
            System.out.println("Error sending email to customer: {}" + e.getMessage());
        }
        listOfBadProducts.clear();
    }

    public void sendGoodEmail(Order orderToEmail) {
        try {
            System.out.println("Sending email to " + orderToEmail.getClient().getEmail());

            var message = new SimpleMailMessage();
            message.setTo(orderToEmail.getClient().getEmail());
            message.setFrom(emailFrom);
            message.setSubject("Pedido aprovado!");    
            message.setText("Olá " + orderToEmail.getClient().getName() + "\n\nSeu pedido foi recebido pelo estoque e foi aprovado :D\n\n");
            message.setText(message.getText() + "Agora o pedido será processado pelo pagamento. Obrigado por comprar com a gente!");
            mailSender.send(message);
        } catch (MailException e) {
            System.out.println("Error sending email to customer: {}" + e.getMessage());
        }
    }

    public void printBadProductList() {
        System.out.println("Bad product List:");
        for (BadProduct badProduct : listOfBadProducts) {
            System.out.println("- " + badProduct);
        }
    }

}
