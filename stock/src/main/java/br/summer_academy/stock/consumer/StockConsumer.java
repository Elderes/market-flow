package br.summer_academy.stock.consumer;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.summer_academy.stock.dto.OrderRecordDTO;
import br.summer_academy.stock.model.Address;
import br.summer_academy.stock.model.Client;
import br.summer_academy.stock.model.Order;
import br.summer_academy.stock.model.Product;
import br.summer_academy.stock.service.StockService;

@Component
public class StockConsumer {    
    @Autowired
    private StockService service;

    @RabbitListener(queues = "${rabbitmq.queue.stock.order}")
    public void receiveOrder(OrderRecordDTO order) {
        Order orderStock = new Order();
        
        List<Product> products = service.mapProducts(order.products());
        Client client = new Client();
        Address address = new Address();

        address.setCountry(order.client().address().country());
        address.setState(order.client().address().state());
        address.setCity(order.client().address().city());
        address.setNeighborhood(order.client().address().neighborhood());
        address.setNumber(order.client().address().number());

        client.setId(order.client().id());
        client.setName(order.client().name());
        client.setCellphone(order.client().cellphone());
        client.setEmail(order.client().email());
        client.setAddress(address);
        
        orderStock.setId(order.id());
        orderStock.setClient(client);
        orderStock.setProducts(products);
        orderStock.setOrderDateTime(order.orderDateTime());
        
        System.out.println("\nClient name: " + order.client().name());
        System.out.println("Order id: " + order.id());
        service.printProductList(products);

        if (service.checkIfAvailable(products)) {
            System.out.println("Products available");
            service.approveOrderAndValue(order); // Foward to payment
            service.sendOrderToStatus(order); // Foward to status same order
            service.sendGoodEmail(orderStock);
        }
        else
        {
            System.out.println("Products unavailable");
            service.disapproveOrderAndValue(order); // Foward to payment
            service.sendBadEmail(orderStock);
        }
    }

}
