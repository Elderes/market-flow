package com.accenture_project.send.consumers;

import com.accenture_project.send.models.OrderModel;
import com.accenture_project.send.services.SendService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderConsumer {

    private final SendService sendService;

    @RabbitListener(queues = "${queue.send.payment}")
    public void consumerMessage(OrderModel order) {
        sendService.saveOrder(order);
    }
}
