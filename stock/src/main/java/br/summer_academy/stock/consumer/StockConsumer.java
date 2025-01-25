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

    @RabbitListener(queues = "${rabbitmq.queue.stock.order}")
    public void receiveOrder(OrderRecordDTO order) {
        System.out.println("Listener.");
        List<Product> products = service.mapProducts(order.products());
        System.out.println(products);
        if (service.checkIfAvailable(products)) {
            // Foward to payment
            System.out.println(order.client().name());
            System.out.println("Products available.");
        }
        else
        {
            // Queue to stock unavailable
            System.out.println("Products unavailable.");
        }
    }
}
