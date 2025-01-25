package accenture_project.Order.producers;

import accenture_project.Order.models.OrderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderProducer {
    private final RabbitTemplate rabbitTemplate;
    private final FanoutExchange fanoutExchange;

    public void publishOrder(OrderModel message) {
        rabbitTemplate.convertAndSend(fanoutExchange.getName(),"", message);
    }
}
