package br.com.market.payments.dto;

import java.util.UUID;

/*
 * Represents the address details of a customer.
 * - Contains fields for the customer's address including country, state, city, neighborhood, street, and number.
 * - Provides a record structure to encapsulate address data in a concise and immutable way.
 */

public record AddressDTO(UUID id,
                         String country,
                         String state,
                         String city,
                         String neighborhood,
                         String street,
                         Integer number) {
}
