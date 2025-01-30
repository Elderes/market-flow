package br.com.accenture_project.order.producers;

import br.com.accenture_project.order.dtos.OrderProducerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/*
 * OrderProducer Class
 *
 * This class is responsible for publishing orders to a RabbitMQ fanout exchange.
 * It uses RabbitTemplate to send messages of type OrderProducerDTO to the exchange.
 * The FanoutExchange ensures that the message is sent to all queues bound to it.
 * The class is annotated as a Spring component and utilizes constructor injection.
 */

@RequiredArgsConstructor
@Component
public class OrderProducer {
    private final RabbitTemplate rabbitTemplate;
    private final FanoutExchange fanoutExchange;

    public void publishOrder(OrderProducerDTO message) {
        rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", message);
    }
}
