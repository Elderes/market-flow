package br.com.accenture_project.payments.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/*
 * Represents the payment information for an order.
 * - Contains fields for total price, payment date and time, order ID, and payment status (hasPaid).
 * - Used to track and manage the payment status of an order.
 */

public record PaymentDTO(BigDecimal totalPrice,
                         LocalDateTime dateTimeOfPayment,
                         UUID orderId,
                         boolean hasPaid) {
}