package br.com.market.payments.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class StockOrderDTO {
    private UUID order_id;
    private UUID client_id;
    private boolean approval;
    private BigDecimal totalValue;
}
