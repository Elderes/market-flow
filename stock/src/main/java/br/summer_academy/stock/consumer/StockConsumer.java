package br.summer_academy.stock.consumer;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import br.summer_academy.stock.dto.OrderRecordDTO;
import br.summer_academy.stock.model.Product;
import br.summer_academy.stock.service.StockService;

public class StockConsumer {    
    @Autowired
    private StockService service;

    @RabbitListener(queues = "#{stockOrderQueue}")
    public void receiveOrder(OrderRecordDTO order) {
        List<Product> products = service.mapProducts(order.products());
        if (service.checkIfAvailable(products)) {
            // Foward to payment
        }
        else
        {
            // Queue to stock unavailable
        }
    }
}
