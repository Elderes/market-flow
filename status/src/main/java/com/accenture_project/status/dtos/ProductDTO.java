package com.accenture_project.status.dtos;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO representing a product with details such as ID, name, quantity, and unit price.
 */

public record ProductDTO(UUID id,
                         String name,
                         Integer quantity,
                         BigDecimal unitPrice) {

}