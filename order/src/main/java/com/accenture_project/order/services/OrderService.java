package com.accenture_project.order.services;

import com.accenture_project.order.dtos.OrderDTO;
import com.accenture_project.order.dtos.ProductDTO;
import com.accenture_project.order.exceptions.NoOrderException;
import com.accenture_project.order.mappers.OrderProducerMapper;
import com.accenture_project.order.mappers.OrderUpdateMapper;
import com.accenture_project.order.models.OrderModel;
import com.accenture_project.order.producers.OrderProducer;
import com.accenture_project.order.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    private final OrderProducer orderProducer;

    private final ClientService clientService;
    private final ProductService productService;

    private final OrderUpdateMapper orderUpdateMapper;
    private final OrderProducerMapper orderProducerMapper;

    private final JavaMailSender mailSender;


    @Value("{$spring.mail.username}")
    private String emailFrom;

    public OrderModel saveOrder(OrderModel order) {
        return orderRepository.save(order);
    }

    public void publishOrder(OrderModel order, List<ProductDTO> productsDTO) {
        var message = orderProducerMapper.toOrderProducerDTO(order, productsDTO);
        orderProducer.publishOrder(message);
    }

    public void validateOrder(OrderModel order, List<ProductDTO> productDTO) {
        clientService.validateClient(order.getClient());
        productService.validateProducts(productDTO);
    }

    public void sendEmail(OrderModel order, List<ProductDTO> productsDTO) {
        try {
            logger.info("Sending email to {}", order.getClient().getEmail());

            var message = new SimpleMailMessage();
            message.setTo(order.getClient().getEmail());
            message.setFrom(emailFrom);
            message.setSubject("Pedido em andamento");
            message.setText("Olá " + order.getClient().getName() + ",\n\n" +
                    "Seu pedido está sendo processado, aguarde email de confirmação de estoque, e email para pagamento!" +
                    "Detalhes do pedido:\n");

            for (ProductDTO product : productsDTO) {
                message.setText(message.getText() +
                        "Produto: " + product.name() + "\n" +
                        "Quantidade: " + product.quantity() + "\n\n");
            }

            message.setText(message.getText() +
                    "Obrigado por comprar conosco! Estamos preparando o pedido.");

            mailSender.send(message);
        } catch (MailException e) {
            logger.error("Error sending email to customer: {}", e.getMessage());
        }
    }

    public List<OrderModel> getOrders() {
        var orders = orderRepository.findAll();

        if (orders.isEmpty()) {
            throw new NoOrderException("There are no orders");
        }

        return orders;
    }

    public OrderModel getOrder(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoOrderException("Order not found with id:" + id));
    }

    public void deleteById(UUID id) {
        if (getOrder(id) == null) {
            throw new NoOrderException("Order not found with id:" + id);
        }
        orderRepository.deleteById(id);
    }

    public void updateOrder(UUID id, OrderDTO orderDTO) {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new NoOrderException("Order not found"));

        order = orderUpdateMapper.toOrderModel(order, orderDTO);

        orderRepository.save(order);
    }
}
