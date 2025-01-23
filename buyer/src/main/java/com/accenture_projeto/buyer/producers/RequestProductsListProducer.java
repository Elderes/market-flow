package com.accenture_projeto.buyer.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RequestProductsListProducer {

    private final RabbitTemplate rabbitTemplate;

    public RequestProductsListProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${exchange-direct}")
    private String exchangeName;

    @Value("${routing.key.request.products}")
    private String routingKey;

    public void publishMessage(String message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
