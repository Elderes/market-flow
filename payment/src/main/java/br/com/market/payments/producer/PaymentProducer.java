package br.com.market.payments.producer;

import br.com.market.payments.dto.PaymentDTO;
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

    @Value("${queue.status.payment}")
    private String routingKey;

    public void publishPayment(PaymentDTO payment) {
        rabbitTemplate.convertAndSend(exchange, routingKey, payment);
    }
}
    