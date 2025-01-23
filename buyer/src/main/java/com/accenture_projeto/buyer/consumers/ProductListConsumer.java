package com.accenture_projeto.buyer.consumers;

import com.accenture_projeto.buyer.models.ProductModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductListConsumer {
    @RabbitListener(queues = "${products.list.queue}")
    public void listenProducts(@Payload List<ProductModel> products) {
        for (ProductModel product : products) {
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getDescription());
            System.out.println(product.getQuantity());
            System.out.println(product.getPrice());
        }

        // TODO: fazer a logica de salvar os produtos disponiveis
    }
}
