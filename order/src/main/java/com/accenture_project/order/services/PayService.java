package com.accenture_project.order.services;

import com.accenture_project.order.exceptions.InvalidPayException;
import com.accenture_project.order.models.PayModel;
import com.accenture_project.order.producers.PaymentProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class PayService {

    private final PaymentProducer paymentProducer;

    public void payOrder(PayModel payModel) {
        paymentProducer.publishPayment(payModel);
    }

    public void validatePayment(PayModel payModel) {
        if (payModel.getCode().isBlank()) {
            throw new InvalidPayException("O código do pedido não pode estar vazio.");
        }

        if (payModel.getValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidPayException("O valor não pode ser negativo.");
        }
    }
}
