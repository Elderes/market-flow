package br.com.accenture_project.stock.consumer;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.accenture_project.stock.dto.OrderRecordDTO;
import br.com.accenture_project.stock.model.Address;
import br.com.accenture_project.stock.model.Client;
import br.com.accenture_project.stock.model.Order;
import br.com.accenture_project.stock.model.Product;
import br.com.accenture_project.stock.service.StockService;

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
        
        System.out.println("\n\nClient name: " + order.client().name());
        System.out.println("Order id: " + order.id());
        service.printBadProductList();
        service.printProductList(products);

        if (service.checkIfAvailable(products)) {
            System.out.println("Products available");
            service.approveOrderAndValue(order); // Foward to payment
            service.sendOrderToStatus(order); // Foward to status same order
            // service.sendGoodEmail(orderStock);
        }
        else
        {
            System.out.println("Products unavailable");
            service.disapproveOrderAndValue(order); // Foward to payment
            service.sendBadEmail(orderStock);
        }
    }

}
