package com.accenture_project.status.consumers;

import com.accenture_project.status.dtos.OrderDTO;
import com.accenture_project.status.dtos.PaymentDTO;
import com.accenture_project.status.mappers.OrderMapper;
import com.accenture_project.status.mappers.PaymentMapper;
import com.accenture_project.status.services.StatusService;
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
public class StatusConsumer {

    private static final Logger logger = LoggerFactory.getLogger(StatusConsumer.class);

    private final StatusService statusService;

    private final PaymentMapper paymentMapper;
    private final OrderMapper orderMapper;

    @RabbitListener(queues = "${queue.status.stock}")
    public void consumerStockMessage(OrderDTO orderDto) {
        var order = orderMapper.toOrderModel(orderDto);
        
        try {
            statusService.saveOrder(order);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @RabbitListener(queues = "${queue.status.payment}")
    public void consumerPaymentMessage(PaymentDTO paymentDto) {
        var payment = paymentMapper.toPaymentModel(paymentDto);

        try {
            statusService.paymentOrder(payment);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
