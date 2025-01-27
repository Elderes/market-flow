package com.accenture_project.status.services;

import com.accenture_project.status.models.OrderModel;
import com.accenture_project.status.models.PaymentModel;
import com.accenture_project.status.models.StatusModel;
import com.accenture_project.status.producers.StatusProducer;
import com.accenture_project.status.repositories.OrderRepository;
import com.accenture_project.status.repositories.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class StatusService {

    private final OrderRepository orderRepository;
    private final StatusRepository statusRepository;
    private final StatusProducer statusProducer;

    public void saveOrder(OrderModel order) {
        if (order.getProducts() != null) {
            order.getProducts().forEach(product -> product.setOrder(order));
        }

        orderRepository.save(order);

        var status = new StatusModel();

        status.setOrderId(order.getId());
        status.setWasPaid(false);
        status.setLastUpdate(LocalDateTime.now());

        statusRepository.save(status);
    }

    public void paymentOrder(PaymentModel payment) {
        var order = orderRepository.findById(payment.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found"));

        var status = statusRepository.findByOrderId(payment.getOrderId());

        status.setWasPaid(true);
        status.setLastUpdate(LocalDateTime.now());

        statusRepository.save(status);

        statusProducer.publishOrder(order);
    }
}
