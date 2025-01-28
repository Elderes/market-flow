package br.com.market.payments.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SendDTO {
    private UUID id;
    private BigDecimal value;
    private LocalDateTime timestamp;
    private UUID orderId;
}