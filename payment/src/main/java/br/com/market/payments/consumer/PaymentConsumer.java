package br.com.market.payments.consumer;

import br.com.market.payments.dto.OrderDTO;
import br.com.market.payments.dto.StockOrderDTO;
import br.com.market.payments.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*
 * Listens for messages from RabbitMQ queues and processes them for payment and stock order updates.
 * - Listens to the queue for order information and saves the payment order.
 * - Listens to the queue for stock approval and processes the stock confirmation.
 * - Handles exceptions and logs errors during message processing.
 */

@RequiredArgsConstructor
@Component
public class PaymentConsumer {

    private static final Logger logger = LoggerFactory.getLogger(PaymentConsumer.class);

    private final PaymentService paymentService;

    @RabbitListener(queues = "${queue.payment.order}")
    public void listenerOrder(OrderDTO orderDTO) {
        try {
            paymentService.savePaymentOrder(orderDTO);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @RabbitListener(queues = "${queue.payment.stock}")
    public void listenStock(StockOrderDTO stockOrderDTO) {
        System.out.println("Received from stock: " + stockOrderDTO.approval());
        try {
            if (!stockOrderDTO.approval()) {
                // TODO -> mandar email
            }
            paymentService.stockConfirmation(stockOrderDTO);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
