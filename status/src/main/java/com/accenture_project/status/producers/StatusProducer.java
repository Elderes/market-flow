package com.accenture_project.status.producers;

import com.accenture_project.status.models.OrderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StatusProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${exchange.direct}")
    private String exchange;

    @Value("${routing.key.send.status}")
    private String routingKey;

    public void publishOrder(OrderModel order) {
        rabbitTemplate.convertAndSend(exchange, routingKey, order);
    }
}
