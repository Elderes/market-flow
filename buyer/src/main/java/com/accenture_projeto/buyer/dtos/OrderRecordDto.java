package com.accenture_projeto.buyer.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record OrderRecordDto(@NotBlank @Pattern(regexp = "\\d{2}\\d{5}\\d{4}",
        message = "O número de celular deve estar no formato XXXXXXXXXXX") String cellphone,
                             List<ProductsListRecordDto> products) {
}
