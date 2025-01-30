package br.com.accenture_project.payments.dto;

import java.util.List;
import java.util.UUID;

/*
 * Represents the stock order information.
 * - Contains fields for the order ID, a list of products in stock, and the approval status.
 * - Used to manage stock-related orders and verify approval for processing.
 */

public record StockOrderDTO(UUID orderId,
                            List<ProductStockDTO> products,
                            boolean approval) {
}