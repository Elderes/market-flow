package com.accenture_projeto.seller.consumer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture_projeto.seller.dto.OrderModelDTO;
import com.accenture_projeto.seller.model.BuyerModel;
import com.accenture_projeto.seller.model.OrderModel;
import com.accenture_projeto.seller.model.ProductModel;
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

    @RabbitListener(queues = "${send.order.queue}")
    public void receiveOrder(OrderModelDTO orderDTO) {
        OrderModel order = new OrderModel(
            new BuyerModel(orderDTO.getBuyer()), 
            orderDTO.getProducts().stream().map(ProductModel::new).collect(Collectors.toList())
        );
        System.out.println(order);
    }
}
