package com.accenture_project.order.dtos;

public record AddressDTO(String country,
                         String state,
                         String city,
                         String neighborhood,
                         String street,
                         Integer number) {
}
