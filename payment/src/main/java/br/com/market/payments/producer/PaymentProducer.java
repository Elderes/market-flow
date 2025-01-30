package br.com.market.payments.producer;

import br.com.market.payments.dto.PaymentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * Responsible for publishing payment information to a message queue.
 * - Uses RabbitTemplate to send payment data to a direct exchange with a specified routing key.
 * - The published payment data is encapsulated in a PaymentDTO.
 * - This component interacts with the messaging system to notify other services about the payment status.
 */

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
