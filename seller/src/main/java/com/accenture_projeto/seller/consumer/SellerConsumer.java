package com.accenture_projeto.seller.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.accenture_projeto.seller.model.OrderModel;

@Component
public class SellerConsumer {
    @RabbitListener(queues = "${request.products.queue}")
    public void sendProductList(String message) {
        System.out.println(message);
    }

    public void receiveOrder(OrderModel order) {

    }
}
