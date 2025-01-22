package com.accenture_projeto.seller.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSender {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    ProductSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendProductList(String exchange, String routinKey, String message) {
        rabbitTemplate.convertAndSend(exchange, routinKey, message);
        System.out.println("Sent: " + message);
    }
}
