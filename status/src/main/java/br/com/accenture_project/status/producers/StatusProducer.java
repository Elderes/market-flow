package br.com.accenture_project.status.producers;

import br.com.accenture_project.status.dtos.StatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Producer class responsible for sending status messages to a RabbitMQ exchange.
 * It publishes the status information to a specified exchange and routing key.
 */

@RequiredArgsConstructor
@Component
public class StatusProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${exchange.direct}")
    private String exchange;

    @Value("${routing.key.send.status}")
    private String routingKey;

    public void publishOrder(StatusDTO statusDTO) {
        rabbitTemplate.convertAndSend(exchange, routingKey, statusDTO);
    }
}
