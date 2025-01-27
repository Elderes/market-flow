package com.accenture_project.send.dtos;

/**
 * Data Transfer Object (DTO) for representing an address.
 * This class contains fields to hold the address information of a client.
 * It is used to transfer address data between layers or services in the application.
 */

public record AddressDTO(String country,
                         String state,
                         String city,
                         String neighborhood,
                         String street,
                         Integer number) {
}
