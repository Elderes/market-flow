package com.accenture_projeto.buyer.consumers;

import com.accenture_projeto.buyer.models.ProductModel;
import com.accenture_projeto.buyer.services.BuyerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductListConsumer {

    private final BuyerService buyerService;

    public ProductListConsumer(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @RabbitListener(queues = "${products.list.queue}")
    public void listenProducts(@Payload List<ProductModel> products) {
        for (ProductModel product : products) {
            buyerService.saveProduct(product);
        }
    }
}
