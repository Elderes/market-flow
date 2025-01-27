package com.accenture_project.status.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
public class PaymentModel {
    private UUID id;
    private BigDecimal value;
    private LocalDateTime datetime;
    private UUID orderId;
}
