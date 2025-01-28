package com.accenture_project.status.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentDTO(UUID id,
                         BigDecimal totalValue,
                         LocalDateTime dateTime,
                         UUID orderId) {

}
