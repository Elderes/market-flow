package br.com.market.payments.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record PayDTO(UUID code, BigDecimal value) {
}
