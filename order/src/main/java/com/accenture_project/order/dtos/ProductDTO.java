package com.accenture_project.order.dtos;

import org.hibernate.validator.constraints.NotBlank;

public record ProductDTO(@NotBlank String name,
                         Integer quantity){
}
