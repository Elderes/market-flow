package com.accenture_project.status.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DTO representing an order with details such as client, products, and order date and time.
 */

public record OrderDTO(UUID id,
                       ClientDTO client,
                       List<ProductDTO> products,
                       LocalDateTime orderDateTime) {
}
