package com.accenture_project.order.dtos;

/*
 * ClientDTO Class
 *
 * A DTO for representing a client.
 */

public record ClientDTO(String name,
                        String cellphone,
                        String email,
                        AddressDTO address) {
}
