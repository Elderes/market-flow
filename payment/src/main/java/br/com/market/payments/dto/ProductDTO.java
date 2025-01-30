package br.com.market.payments.dto;

import java.math.BigDecimal;
import java.util.UUID;

/*
 * Represents the details of a product in an order.
 * - Contains fields for product name, quantity ordered, and unit price.
 * - Used to track and manage product-related information in an order.
 */

public record ProductDTO(String name,
                         Integer quantity,
                         BigDecimal unitPrice){
}