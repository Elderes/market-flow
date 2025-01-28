package br.summer_academy.stock.consumer;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.summer_academy.stock.dto.OrderRecordDTO;
import br.summer_academy.stock.model.Product;
import br.summer_academy.stock.service.StockService;

@Component
public class StockConsumer {    
    @Autowired
    private StockService service;

    @RabbitListener(queues = "${rabbitmq.queue.stock.order}")
    public void receiveOrder(OrderRecordDTO order) {
        List<Product> products = service.mapProducts(order.products());
        
        System.out.println("\n\nClient name: " + order.client().name());
        System.out.println("Order id: " + order.id());
        service.printProductList(products);

        if (service.checkIfAvailable(products)) {
            System.out.println("Products available");
            service.approveOrderAndValue(order); // Foward to payment
            service.sendOrderToStatus(order); // Foward to status same order
        }
        else
        {
            System.out.println("Products unavailable");
            service.disapproveOrderAndValue(order); // Foward to payment
        }
    }

}
