package br.com.market.payments.consumer;

import br.com.market.payments.dto.PayDTO;
import br.com.market.payments.dto.StockOrderDTO;
import br.com.market.payments.model.OrderModel;
import br.com.market.payments.service.OrderService;
import br.com.market.payments.service.PayService;
import br.com.market.payments.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentConsumer {

    private final OrderService orderService;
    private final StockService stockService;
    private final PayService payService;

    @RabbitListener(queues = "${queue.payment.order}")
    public void listenerOrder(OrderModel orderModel) {
        orderService.saveOrder(orderModel);
    }

    @RabbitListener(queues = "${queue.payment.stock}")
    public void listenStock(StockOrderDTO stockOrderDTO) {
        if (stockOrderDTO.isApproval()) {
            stockService.saveStock(stockOrderDTO);
        }
    }

    @RabbitListener(queues = "${queue.payment.order.pay}")
    public void listenStock(PayDTO payDTO) {
        payService.pay(payDTO);
    }
}
