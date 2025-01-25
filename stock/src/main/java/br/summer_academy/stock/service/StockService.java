package br.summer_academy.stock.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class StockService {
    @RabbitListener(queues = "")
    private void receiveOrder() {

    }
}
