package com.accenture_project.order.producers;

import com.accenture_project.order.dtos.OrderProducerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderProducer {
    private final RabbitTemplate rabbitTemplate;
    private final FanoutExchange fanoutExchange;

    public void publishOrder(OrderProducerDTO message) {
        rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", message);
    }
}
