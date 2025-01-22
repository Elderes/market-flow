package com.accenture_projeto.buyer.dtos;

import jakarta.validation.constraints.NotBlank;

public record AddressRecordDto( @NotBlank String country,
                                @NotBlank String state,
                                @NotBlank String city,
                                @NotBlank String neighborhood,
                                @NotBlank String street,
                                Integer number) {
}
