package br.com.market.payments.service;

import br.com.market.payments.dto.PaymentDTO;
import br.com.market.payments.mapper.PaymentMapper;
import br.com.market.payments.model.OrderModel;
import br.com.market.payments.model.PaymentModel;
import br.com.market.payments.model.ProductModel;
import br.com.market.payments.producer.PaymentProducer;
import br.com.market.payments.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final PaymentProducer paymentProducer;

    public BigDecimal calculateTotal(List<ProductModel> products) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (ProductModel product : products) {
            if (product.getUnitPrice() != null && product.getQuantity() > 0) {
                var productTotal = product.getUnitPrice().multiply(BigDecimal.valueOf(product.getQuantity()));
                totalPrice = totalPrice.add(productTotal);
            }
        }

        return totalPrice;
    }

    public PaymentModel savePayment(UUID orderId, OrderModel order) {
        var payment = new PaymentModel();

        payment.setTotalPrice(order.getTotalPrice());
        payment.setDateTimeOfPayment(LocalDateTime.now());
        payment.setOrderId(orderId);

        return paymentRepository.save(payment);
    }

    public void paymentProducer(PaymentDTO paymentDTO) {
        paymentProducer.publishPayment(paymentDTO);
    }
}
