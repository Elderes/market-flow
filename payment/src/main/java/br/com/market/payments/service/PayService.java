package br.com.market.payments.service;

import br.com.market.payments.dto.PayDTO;
import br.com.market.payments.model.OrderModel;
import br.com.market.payments.producer.PaymentProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PayService {

    private final StockService stockService;
    private final OrderService orderService;
    private final PaymentProducer paymentProducer;

    public void pay(PayDTO payDTO) {
        var stock = stockService.getStockOrder(payDTO.getCode());
        var order = orderService.getOrderById(stock.getOrder_id());

        if (order != null && payDTO.getValue().compareTo(stock.getTotalValue()) >= 0) {
            //TODO -> mandar email
            sendQueue(order);
        }
    }

    public void sendQueue(OrderModel orderModel) {
        paymentProducer.publishSend(orderModel);
    }
}
