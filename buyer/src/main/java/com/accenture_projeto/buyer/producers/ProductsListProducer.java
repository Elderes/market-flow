package com.accenture_projeto.buyer.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductsListProducer {

    final RabbitTemplate rabbitTemplate;

    public ProductsListProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${broker.exchange.request.list.products.name}")
    private String exchangeName;

    @Value("${routing.key}")
    private String routingKey;

    public void publishMessage(String message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
