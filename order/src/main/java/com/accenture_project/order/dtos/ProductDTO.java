package com.accenture_project.order.dtos;

import java.math.BigDecimal;

/*
 * ProductDTO Class
 *
 * A DTO for representing a product, including its name, quantity, and unit price.
 */

public record ProductDTO(String name,
                         Integer quantity,
                         BigDecimal unitPrice){
}
