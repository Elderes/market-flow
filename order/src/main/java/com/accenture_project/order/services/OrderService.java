package com.accenture_project.order.services;

import com.accenture_project.order.models.OrderModel;
import com.accenture_project.order.producers.OrderProducer;
import com.accenture_project.order.producers.PaymentProducer;
import com.accenture_project.order.repositories.OrderRepository;
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
