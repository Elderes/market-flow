package br.com.accenture_project.payments.dto;

import java.math.BigDecimal;
import java.util.UUID;

/*
 * Represents the payment details for an order.
 * - Contains fields for the payment code (order identifier) and the payment amount (value).
 * - Used to process and validate payments for orders.
 */

public record PayDTO(UUID code, BigDecimal value) {
}