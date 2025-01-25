package accenture_project.Order.services;

import accenture_project.Order.models.OrderModel;
import accenture_project.Order.producers.OrderProducer;
import accenture_project.Order.producers.PaymentProducer;
import accenture_project.Order.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;
    private final PaymentProducer paymentProducer;

    public OrderModel saveOrder(OrderModel order) {
        return orderRepository.save(order);
    }

    public void publishOrder(OrderModel order) {
        orderProducer.publishOrder(order);
    }
}
