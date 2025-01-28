package br.com.market.payments.dto;

import java.util.UUID;

public record AddressDTO(UUID id,
                         String country,
                         String state,
                         String city,
                         String neighborhood,
                         String street,
                         Integer number) {
}
