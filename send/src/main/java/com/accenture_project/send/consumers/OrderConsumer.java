package com.accenture_project.send.consumers;

import com.accenture_project.send.models.OrderModel;
import com.accenture_project.send.services.OrderService;
import com.accenture_project.send.services.SendService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ message consumer responsible for processing orders and sending email notifications.
 *
 * - consumerMessage: Receives a message with order details, saves it to the database, and sends a confirmation email.
 *                    If any error occurs during processing, it is logged.
 */

@RequiredArgsConstructor
@Component
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    private final OrderService orderService;
    private final SendService sendService;

    @RabbitListener(queues = "${queue.send.status}")
    public void consumerMessage(OrderModel order) {
        try {
            orderService.saveOrder(order);
            sendService.sendEmail(order);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
