package com.accenture_project.order.services;

import com.accenture_project.order.models.PayModel;
import com.accenture_project.order.producers.PaymentProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PayService {

    private final PaymentProducer paymentProducer;

    public void payOrder(PayModel payModel) {
        paymentProducer.publishPayment(payModel);
    }
}
