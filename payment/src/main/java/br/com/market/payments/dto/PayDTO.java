package br.com.market.payments.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PayDTO {
    private Long code;
    private BigDecimal value;
    private LocalDateTime dateTime;
}
