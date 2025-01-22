package com.accenture_projeto.buyer.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record BuyerRecordDto(@NotBlank String name,
                             @NotBlank @Email String email,
                             AddressRecordDto address,
                             @NotBlank @Pattern(regexp = "\\d{2}\\d{5}\\d{4}",
                                     message = "O número de celular deve estar no formato XXXXXXXXXXX") String cellphone) {
}
