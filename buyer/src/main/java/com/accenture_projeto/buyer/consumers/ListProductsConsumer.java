package com.accenture_projeto.buyer.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ListProductsConsumer {
    @RabbitListener(queues = "${broker.queue.request.list.products.name}")
    public void listenProducts(@Payload String products) {
        System.out.println(products);
    }
}
