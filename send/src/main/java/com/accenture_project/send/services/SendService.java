package com.accenture_project.send.services;

import com.accenture_project.send.exceptions.EmailSendingException;
import com.accenture_project.send.models.OrderModel;
import com.accenture_project.send.models.ProductModel;
import com.accenture_project.send.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SendService {

    private final OrderRepository orderRepository;
    private final AddressService addressService;
    private final ClientService clientService;
    private final ProductService productService;
    private final JavaMailSender mailSender;

    @Value("{$spring.mail.username}")
    private String emailFrom;

    // method that processes data and saves it in mysql
    public void saveOrder(OrderModel order) {
        var address = addressService.verifyAddress(order.getClient().getAddress());
        var client = clientService.verifyClient(order.getClient());
        client.setAddress(address);

        order.setClient(client);

        var products = new ArrayList<ProductModel>();

        for (var product : order.getProducts()) {
            var verifiedProduct = productService.verifyProduct(product);
            verifiedProduct.setOrder(order);
            products.add(verifiedProduct);
        }

        order.setProducts(products);

        orderRepository.save(order);
    }

    public List<OrderModel> getOrders() {
        return orderRepository.findAll();
    }

    public void sendEmail(OrderModel order) {
        try {
            var message = new SimpleMailMessage();
            message.setTo(order.getClient().getEmail());
            message.setFrom(emailFrom);
            message.setSubject("Pedido concluído");
            message.setText("Olá " + order.getClient().getName() + ",\n\n" +
                    "Seu pedido está sendo enviado para o seguinte endereço:\n" +
                    order.getClient().getAddress().getStreet() + ", " +
                    order.getClient().getAddress().getNumber() + " - " +
                    order.getClient().getAddress().getNeighborhood() + ", " +
                    order.getClient().getAddress().getCity() + " - " +
                    order.getClient().getAddress().getState() + "\n\n" +
                    "Detalhes do pedido:\n");

            for (ProductModel product : order.getProducts()) {
                message.setText(message.getText() +
                        "Produto: " + product.getName() + "\n" +
                        "Quantidade: " + product.getQuantity() + "\n\n");
            }

            message.setText(message.getText() +
                    "Obrigado por comprar conosco! Estamos preparando o envio.");

            mailSender.send(message);
            System.out.println(order.getClient().getEmail());
        } catch (MailException e) {
            throw new EmailSendingException("Error sending email to customer" + e.getMessage());
        }
    }
}
