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

    // method that consumes messages from the queue, it saves the request and sends it to the email sending service
    @RabbitListener(queues = "${queue.send.payment}")
    public void consumerMessage(OrderModel order) {
        sendService.saveOrder(order);
        sendService.sendEmail(order);
    }
}
