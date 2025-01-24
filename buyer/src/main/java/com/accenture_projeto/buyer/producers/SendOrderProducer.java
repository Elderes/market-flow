package com.accenture_projeto.buyer.producers;

import com.accenture_projeto.buyer.models.OrderModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SendOrderProducer {

    private final RabbitTemplate rabbitTemplate;

    public SendOrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${exchange-direct}")
    private String exchangeName;

    @Value("${routing.key.send.order}")
    private String routingKey;

    public void publishMessage(OrderModel message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
