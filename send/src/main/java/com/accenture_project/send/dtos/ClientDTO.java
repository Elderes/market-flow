package com.accenture_project.send.dtos;

/**
 * Data Transfer Object (DTO) for representing a client.
 * This class contains the personal details of a client, including their address.
 * It is used to transfer client data between layers or services in the application.
 */

public record ClientDTO(String name,
                        String cellphone,
                        String email,
                        AddressDTO address) {
}
