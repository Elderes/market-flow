package com.accenture_project.status.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentDTO(BigDecimal totalPrice,
                         LocalDateTime dateTimeOfPayment,
                         UUID orderId,
                         boolean hasPaid) {

}
