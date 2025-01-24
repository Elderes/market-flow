package com.accenture_projeto.seller.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductModelDTO {
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;
}
