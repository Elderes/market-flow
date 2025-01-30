package br.com.accenture_project.payments.dto;

import java.math.BigDecimal;
import java.util.UUID;

/*
 * Represents the details of a product in stock.
 * - Contains fields for product ID, name, available quantity, and unit price.
 * - Used to track and manage product stock information in the inventory.
 */

public record ProductStockDTO(UUID id,
                              String name,
                              Integer quantity,
                              BigDecimal unitPrice) {
}