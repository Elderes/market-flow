package com.accenture_project.order.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor
public class PayModel {
    private String code;
    private BigDecimal value;
    private LocalDateTime dateTime;
}
