package br.com.accenture_project.status.dtos;

import java.util.UUID;

/**
 * A DTO (Data Transfer Object) representing an address.
 * This class encapsulates address details such as country, state, city, neighborhood, street, and number.
 */

public record AddressDTO(UUID id,
                         String country,
                         String state,
                         String city,
                         String neighborhood,
                         String street,
                         Integer number) {

}