package com.accenture_project.send.services;

import com.accenture_project.send.models.OrderModel;
import com.accenture_project.send.models.ProductModel;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for sending emails related to order completion.
 *
 * - sendEmail: Sends an email to the customer with order details. Logs errors in case of failure.
 */

@RequiredArgsConstructor
@Service
public class SendService {

    private static final Logger logger = LoggerFactory.getLogger(SendService.class);

    private final JavaMailSender mailSender;

    @Value("{$spring.mail.username}")
    private String emailFrom;

    public void sendEmail(OrderModel order) {
        try {
            logger.info("Sending email to {}", order.getClient().getEmail());

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
        } catch (MailException e) {
            logger.error("Error sending email to customer: {}", e.getMessage());
        }
    }
}