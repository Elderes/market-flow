package br.com.market.payments.dto;

import java.util.UUID;

/*
 * Represents a client's personal and contact details.
 * - Contains fields for client ID, name, cellphone, email, and an address object.
 * - The address field is an instance of the `AddressDTO` class, representing the client's address.
 */

public record ClientDTO(UUID id, String name,
                        String cellphone,
                        String email,
                        AddressDTO address) {
}