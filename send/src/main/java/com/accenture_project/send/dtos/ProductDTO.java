package com.accenture_project.send.dtos;

import org.hibernate.validator.constraints.NotBlank;

public record ProductDTO(@NotBlank String name,
                         Integer quantity){
}
