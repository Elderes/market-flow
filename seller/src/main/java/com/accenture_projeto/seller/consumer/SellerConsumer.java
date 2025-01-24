package com.accenture_projeto.seller.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture_projeto.seller.model.OrderModel;
import com.accenture_projeto.seller.service.SellerProducer;

@Component
public class SellerConsumer {
    @Autowired
    SellerProducer productSender;

    @RabbitListener(queues = "${request.products.queue}")
    public void sendProductList(String message) {
        productSender.sendProductList();
        System.out.println("Sent products list!");
    }

    public void receiveOrder(OrderModel order) {

    }
}
