package br.com.accenture_project.status.consumers;

import br.com.accenture_project.status.dtos.OrderDTO;
import br.com.accenture_project.status.dtos.PaymentDTO;
import br.com.accenture_project.status.services.StatusService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Listens to messages from RabbitMQ queues and processes them.
 * This class consumes messages related to order status and payment status, invoking corresponding service methods.
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
