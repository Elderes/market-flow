package br.com.accenture_project.order.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/*
 * OrderProducerDTO Class
 *
 * A DTO for representing an order to be produced, including the order ID, client, products, and order date/time.
 */

public record OrderProducerDTO(UUID id,
                               ClientDTO client,
                               List<ProductDTO> products,
                               LocalDateTime orderDateTime) {
}
