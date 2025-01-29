package com.accenture_project.status.services;

import com.accenture_project.status.dtos.OrderDTO;
import com.accenture_project.status.dtos.PaymentDTO;
import com.accenture_project.status.exceptions.NoStatusException;
import com.accenture_project.status.mappers.StatusMapper;
import com.accenture_project.status.models.StatusModel;
import com.accenture_project.status.producers.StatusProducer;
import com.accenture_project.status.repositories.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class StatusService {

    private final StatusRepository statusRepository;

    private final StatusProducer statusProducer;

    private final StatusMapper statusMapper;

    public void saveOrderStatus(OrderDTO order) {
        var status = new StatusModel();

        status.setOrderId(order.id());
        status.setWasPaid(false);
        status.setLastUpdate(LocalDateTime.now());
        status.setEmailClient(order.client().email());

        statusRepository.save(status);
    }

    public void paymentOrder(PaymentDTO payment) {

        var status = statusRepository.findByOrderId(payment.orderId()).orElseThrow(() -> new NoStatusException("Order not found"));

        status.setWasPaid(payment.hasPaid());
        status.setLastUpdate(LocalDateTime.now());
        status.setTotalPrice(payment.totalPrice());

        statusRepository.save(status);

        var statusDTO = statusMapper.toStatusModel(status);

        statusProducer.publishOrder(statusDTO);
    }

    public List<StatusModel> getAllStatus() {
        var status = statusRepository.findAll();

        if (status.isEmpty()) {
            throw new NoStatusException("There are no status");
        }

        return status;
    }

    public StatusModel getStatus(UUID id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new NoStatusException("Status not found with id:" + id));
    }

    public void deleteById(UUID id) {
        if (getStatus(id) == null) {
            throw new NoStatusException("Status not found with id:" + id);
        }
        statusRepository.deleteById(id);
    }
}
