package com.accenture_project.order.dtos;

/*
 * AddressDTO Class
 *
 * A DTO for representing an address.
 */

public record AddressDTO(String country,
                         String state,
                         String city,
                         String neighborhood,
                         String street,
                         Integer number) {
}
