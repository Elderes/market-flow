package br.com.market.payments.mapper;

import br.com.market.payments.dto.PaymentDTO;
import br.com.market.payments.model.PaymentModel;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public PaymentDTO toPaymentDTOMapper(PaymentModel paymentModel){
        return new PaymentDTO(paymentModel.getId(), paymentModel.getTotalPrice(), paymentModel.getDateTimeOfPayment(), paymentModel.getOrderId());
    }
}
