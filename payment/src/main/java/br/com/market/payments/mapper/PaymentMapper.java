package br.com.market.payments.mapper;

import br.com.market.payments.dto.PaymetDTO;
import br.com.market.payments.model.PaymentModel;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public PaymetDTO toPaymentDTOMapper(PaymentModel paymentModel){
        return new PaymetDTO(paymentModel.getId(), paymentModel.getTotalPrice(), paymentModel.getDateTimeOfPayment(), paymentModel.getOrderId());
    }
}
