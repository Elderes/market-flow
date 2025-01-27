package com.accenture_project.status.mappers;

import com.accenture_project.status.dtos.PaymentDTO;
import com.accenture_project.status.models.PaymentModel;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public PaymentModel toPaymentModel(PaymentDTO paymentDTO) {
        var payment = new PaymentModel();

        payment.setId(paymentDTO.id());
        payment.setOrderId(paymentDTO.orderId());
        payment.setDatetime(paymentDTO.dateTime());
        payment.setValue(paymentDTO.value());

        return payment;
    }
}
