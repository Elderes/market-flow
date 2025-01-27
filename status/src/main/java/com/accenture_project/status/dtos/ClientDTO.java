package com.accenture_project.status.dtos;

import java.util.UUID;

public record ClientDTO(UUID id,
                        String name,
                        String cellphone,
                        String email,
                        AddressDTO address) {

}
