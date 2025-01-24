package com.accenture_projeto.buyer.dtos;

import jakarta.validation.constraints.NotBlank;

public record ProductsListRecordDto(@NotBlank String nameProduct,
                                    Integer quantity) {
}
