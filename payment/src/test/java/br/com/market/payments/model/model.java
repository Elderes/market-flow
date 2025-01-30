package br.com.market.payments.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentModelTest {
    private PaymentModel payment;

    @BeforeEach
    void setUp() {
        payment = new PaymentModel();
    }

    @Test
    void testSetAndGetId() {
        UUID id = UUID.randomUUID();
        payment.setId(id);
        assertThat(payment.getId()).isEqualTo(id);
    }

    @Test
    void testSetAndGetTotalPrice() {
        BigDecimal price = new BigDecimal("99.99");
        payment.setTotalPrice(price);
        assertThat(payment.getTotalPrice()).isEqualTo(price);
    }

    @Test
    void testSetAndGetDateTimeOfPayment() {
        LocalDateTime now = LocalDateTime.now();
        payment.setDateTimeOfPayment(now);
        assertThat(payment.getDateTimeOfPayment()).isEqualTo(now);
    }

    @Test
    void testSetAndGetOrderId() {
        UUID orderId = UUID.randomUUID();
        payment.setOrderId(orderId);
        assertThat(payment.getOrderId()).isEqualTo(orderId);
    }

    @Test
    void testSetAndGetOrderArrivalTime() {
        LocalDateTime arrivalTime = LocalDateTime.now().plusDays(2);
        payment.setOrderArrivalTime(arrivalTime);
        assertThat(payment.getOrderArrivalTime()).isEqualTo(arrivalTime);
    }

    @Test
    void testSetAndGetHasPaid() {
        payment.setHasPaid(true);
        assertThat(payment.isHasPaid()).isTrue();
    }

    @Test
    void testSetAndGetStockConfirmed() {
        payment.setStockConfirmed(true);
        assertThat(payment.isStockConfirmed()).isTrue();
    }

    @Test
    void testSetAndGetEmailClient() {
        String email = "cliente@example.com";
        payment.setEmailClient(email);
        assertThat(payment.getEmailClient()).isEqualTo(email);
    }
}