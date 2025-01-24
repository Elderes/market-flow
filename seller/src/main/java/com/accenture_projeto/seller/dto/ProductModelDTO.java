package com.accenture_projeto.seller.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductModelDTO {
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;
}
