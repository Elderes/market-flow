package br.com.market.payments.dto;

public record AddressDTO(String country,
                         String state,
                         String city,
                         String neighborhood,
                         String street,
                         Integer number) {
}
