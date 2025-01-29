package com.accenture_project.status.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO representing a payment with details such as total price, payment date and time, order ID, and payment status.
 */

public record PaymentDTO(BigDecimal totalPrice,
                         LocalDateTime dateTimeOfPayment,
                         UUID orderId,
                         boolean hasPaid) {

}
