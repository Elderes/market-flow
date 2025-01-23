package com.accenture_projeto.buyer.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ListProductsConsumer {
    @RabbitListener(queues = "${}") // TODO: colocar a fila que abraao vai fazer
    public void listenProducts(@Payload String products) {
        System.out.println(products);
    }
}
