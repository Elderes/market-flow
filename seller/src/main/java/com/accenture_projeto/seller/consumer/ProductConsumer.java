package com.accenture_projeto.seller.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {
    @RabbitListener(queues = "my-queue")
    public void receiveProductList(String message) {
        System.out.println("Received: " + message);
    }
}
