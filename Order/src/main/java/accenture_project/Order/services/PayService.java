package accenture_project.Order.services;

import accenture_project.Order.models.PayModel;
import accenture_project.Order.producers.PaymentProducer;
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
