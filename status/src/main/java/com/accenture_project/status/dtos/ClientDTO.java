package com.accenture_project.status.dtos;

import java.util.UUID;

/**
 * DTO representing a client with details like name, cellphone, email, and address.
 */

public record ClientDTO(UUID id,
                        String name,
                        String cellphone,
                        String email,
                        AddressDTO address) {

}
