package com.accenture_project.order.dtos;

import java.math.BigDecimal;

public record PayDTO(String code,
                     BigDecimal value) {
}
