package br.com.market.payments.dto;

import java.util.List;
import java.util.UUID;

public record StockOrderDTO(UUID orderId,
                            List<ProductStockDTO> products,
                            boolean approval) {
}
