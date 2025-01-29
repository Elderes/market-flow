package com.accenture_project.order.dtos;

import java.math.BigDecimal;

public record ProductDTO(String name,
                         Integer quantity,
                         BigDecimal unitPrice){
}
