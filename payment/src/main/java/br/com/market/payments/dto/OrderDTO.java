package br.com.market.payments.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/*
 * Represents an order placed by a client.
 * - Contains fields for order ID, client details (as a `ClientDTO`), order date and time, and a list of products (as `ProductDTO`).
 * - Encapsulates the order information necessary for payment processing.
 */

public record OrderDTO(UUID id,
                       ClientDTO client,
                       LocalDateTime orderDateTime,
                       List<ProductDTO> products) {
}