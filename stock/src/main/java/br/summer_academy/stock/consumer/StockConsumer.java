package br.summer_academy.stock.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import br.summer_academy.stock.repository.StockRepository;

public class StockConsumer {
    @Autowired
    private StockRepository repository;

    @RabbitListener(queues = "#{stockOrderQueue}")
    public void receiveOrder() {
        
    }
}
