package com.accenture_project.order.producers;

import com.accenture_project.order.models.PayModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${exchange.direct}")
    private String exchange;

    @Value("${routing.key.payment.order}")
    private String routingKey;

    public void publishPayment(PayModel pay) {
        rabbitTemplate.convertAndSend(exchange, routingKey, pay);
    }
}
