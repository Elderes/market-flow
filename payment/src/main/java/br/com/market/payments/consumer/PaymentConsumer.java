package br.com.market.payments.consumer;

import br.com.market.payments.dto.OrderDTO;
import br.com.market.payments.dto.StockOrderDTO;
import br.com.market.payments.mapper.OrderMapper;
import br.com.market.payments.mapper.PaymentMapper;
import br.com.market.payments.mapper.StockOrderMapper;
import br.com.market.payments.service.OrderService;
import br.com.market.payments.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentConsumer {

    private static final Logger logger = LoggerFactory.getLogger(PaymentConsumer.class);

    private final OrderMapper orderMapper;
    private final StockOrderMapper stockOrderMapper;
    private final PaymentMapper paymentMapper;

    private final OrderService orderService;
    private final PaymentService paymentService;

    @RabbitListener(queues = "${queue.payment.order}")
    public void listenerOrder(OrderDTO orderDTO) {
        var orderModel = orderMapper.toOrderModel(orderDTO);

        try {
            orderService.saveOrder(orderModel);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


    @RabbitListener(queues = "${queue.payment.stock}")
    public void listenStock(StockOrderDTO stockOrderDTO) {
        try {
            if (!stockOrderDTO.approval()) {
                // TODO -> mandar email
            }
                var order = orderService.getOrder(stockOrderDTO.orderId());
                var stock = stockOrderMapper.toStockModel(stockOrderDTO, order);

                order = orderService.updateOrder(order, stock);
                var payment = paymentService.savePayment(order.getId(), order);

                var paymentDTO = paymentMapper.toPaymentDTOMapper(payment);

                paymentService.paymentProducer(paymentDTO);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


}
