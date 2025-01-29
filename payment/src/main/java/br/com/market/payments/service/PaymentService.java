package br.com.market.payments.service;

import br.com.market.payments.dto.*;
import br.com.market.payments.exception.InvalidValueException;
import br.com.market.payments.exception.PaymentNotFoundException;
import br.com.market.payments.model.PaymentModel;
import br.com.market.payments.producer.PaymentProducer;
import br.com.market.payments.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository paymentRepository;

    private final PaymentProducer paymentProducer;

    private final JavaMailSender mailSender;

    @Value("{$spring.mail.username}")
    private String emailFrom;

    public void savePaymentOrder(OrderDTO order) {
        var payment = new PaymentModel();

        payment.setOrderId(order.id());
        payment.setDateTimeOfPayment(LocalDateTime.now());
        payment.setHasPaid(false);
        payment.setTotalPrice(calculateTotal(order.products()));
        payment.setStockConfirmed(false);
        payment.setEmailClient(order.client().email());

        paymentRepository.save(payment);
    }

    public void stockConfimation(StockOrderDTO stockOrder) {
        if (!stockOrder.approval()) {
            // email
        }

        var payment = paymentRepository.findByOrderId(stockOrder.orderId()).orElseThrow(() -> new PaymentNotFoundException("Payment not found"));

        payment.setStockConfirmed(true);
        sendEmail(payment);
    }

    public BigDecimal calculateTotal(List<ProductDTO> products) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (ProductDTO product : products) {
            if (product.unitPrice() != null && product.quantity() > 0) {
                var productTotal = product.unitPrice().multiply(BigDecimal.valueOf(product.quantity()));
                totalPrice = totalPrice.add(productTotal);
            }
        }

        return totalPrice;
    }

    public void pay(PayDTO payDTO) {
        var payment = findPaymentById(payDTO.code());

        if (payment.getTotalPrice().compareTo(payDTO.value()) < 0) {
            throw new InvalidValueException("Payment amount less than total order amount!");
        }

        payment.setDateTimeOfPayment(LocalDateTime.now());
        payment.setHasPaid(true);

        paymentRepository.save(payment);

        var paymentDTO = new PaymentDTO(payment.getTotalPrice(), payment.getDateTimeOfPayment(), payment.getOrderId(), payment.isHasPaid());

        paymentProducer.publishPayment(paymentDTO);
    }

    public PaymentModel findPaymentById(UUID id) {
        return paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
    }

    public List<PaymentModel> findAllPayments() {
        return paymentRepository.findAll();
    }

    public void sendEmail(PaymentModel paymentModel) {
        try {
            logger.info("Sending email");

            var message = new SimpleMailMessage();
            message.setTo(paymentModel.getEmailClient());
            message.setFrom(emailFrom);
            message.setSubject("Pedido esperando pagamento");
            message.setText("Código de pagamento: " + paymentModel.getId() + " \nValor do pedido:" + paymentModel.getTotalPrice().setScale(2) + "R$.");

            message.setText(message.getText() +
                    "Obrigado por comprar conosco! Estamos preparando o pedido.");

            mailSender.send(message);
        } catch (MailException e) {
            logger.error("Error sending email to customer: {}", e.getMessage());
        }
    }

    public void deletePayment(UUID id) {
        var payment = paymentRepository.findById(id);

        paymentRepository.deleteById(payment.get().getId());
    }
}
