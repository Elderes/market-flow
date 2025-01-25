package com.accenture_project.order.dtos;

import org.hibernate.validator.constraints.NotBlank;

public record AddressDTO(@NotBlank String country,
                         @NotBlank String state,
                         @NotBlank String city,
                         @NotBlank String neighborhood,
                         @NotBlank String street,
                         Integer number) {
}
