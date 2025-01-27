package com.accenture_project.status.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@Data
public class PaymentModel {
    private UUID id;
    private BigDecimal value;
    private LocalDateTime datetime;
    private String code;
}
