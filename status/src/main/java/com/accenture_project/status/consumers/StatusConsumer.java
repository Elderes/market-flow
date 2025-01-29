package com.accenture_project.status.consumers;

import com.accenture_project.status.dtos.OrderDTO;
import com.accenture_project.status.dtos.PaymentDTO;
import com.accenture_project.status.services.StatusService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ message consumer responsible for processing orders and sending email notifications.
 * <p>
 * - consumerMessage: Receives a message with order details, saves it to the database, and sends a confirmation email.
 * If any error occurs during processing, it is logged.
 */

@RequiredArgsConstructor
@Component
public class StatusConsumer {

    private static final Logger logger = LoggerFactory.getLogger(StatusConsumer.class);

    private final StatusService statusService;

    @RabbitListener(queues = "${queue.status.stock}")
    public void consumerStockMessage(OrderDTO orderDto) {
        try {
            statusService.saveOrderStatus(orderDto);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @RabbitListener(queues = "${queue.status.payment}")
    public void consumerPaymentMessage(PaymentDTO paymentDto) {
        try {
            statusService.paymentOrder(paymentDto);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
