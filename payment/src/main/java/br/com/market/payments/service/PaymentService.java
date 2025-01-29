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

/*
 * Service layer responsible for managing payment operations, including:
 * - Saving payment orders to the database.
 * - Confirming stock and updating payment status.
 * - Calculating the total price of an order.
 * - Handling payment transactions and ensuring correctness of payment amounts.
 * - Sending email notifications regarding payment statuses (approved or rejected).
 * - Publishing payment status to a message queue after a successful payment.
 */


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

    public void stockConfirmation(StockOrderDTO stockOrder) {
        var payment = paymentRepository.findByOrderId(stockOrder.orderId())
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
    
        payment.setStockConfirmed(true);
        payment.setHasPaid(false);

        paymentRepository.save(payment);
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

        if (payment.getTotalPrice().compareTo(payDTO.value()) == 0) {
            logger.error("Payment amount less than total order amount!");

            sendErrorEmail(payment);

            throw new InvalidValueException("Payment amount less than total order amount!");
        }

        payment.setDateTimeOfPayment(LocalDateTime.now());
        payment.setHasPaid(true);

        paymentRepository.save(payment);

        var paymentDTO = new PaymentDTO(payment.getTotalPrice(), payment.getDateTimeOfPayment(), payment.getOrderId(), payment.isHasPaid());

        logger.info("Payment approved.");

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
            message.setSubject("Pedido aprovado");
            message.setText("Seu pedido foi recebido pelo estoque e foi aprovado :D\nAgora o pedido está esperando o pagamento\nCódigo de pagamento: " + paymentModel.getId() + " \nValor do pedido:" + paymentModel.getTotalPrice().setScale(2) + "R$.");

            message.setText(message.getText() +
                    "\n\nObrigado por comprar conosco! Estamos esperando o pagamento.");

            mailSender.send(message);
        } catch (MailException e) {
            logger.error("Error sending email to customer: {}", e.getMessage());
        }
    }

    public void sendErrorEmail(PaymentModel paymentModel) {
        try {
            logger.info("Sending error email");



            var message = new SimpleMailMessage();
            message.setTo(paymentModel.getEmailClient());
            message.setFrom(emailFrom);
            message.setSubject("Pagamento recusado!");
            message.setText("Seu pagamento foi recusado! Valor recebido é menor que o valor total do pedido!\nTente pagar novamente\nCódigo de pagamento: " + paymentModel.getId() + " \nValor do pedido:" + paymentModel.getTotalPrice().setScale(2) + "R$.");

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
