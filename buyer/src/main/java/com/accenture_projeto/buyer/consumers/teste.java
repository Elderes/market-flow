package com.accenture_projeto.buyer.consumers;

import com.accenture_projeto.buyer.models.ProductModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class teste {
//    @RabbitListener(queues = "${request.products.queue}")
//    public void listenProducts(@Payload String products) {
//        System.out.println(products);
//    }
//
//    @RabbitListener(queues = "${send.order.queue}")
//    public void listenOrders(@Payload List<ProductModel> productModels) {
//        for (ProductModel productModel : productModels) {
//            System.out.println(productModel.getId());
//        }
//    }
}
