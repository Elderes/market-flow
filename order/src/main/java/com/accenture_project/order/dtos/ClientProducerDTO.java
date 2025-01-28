package com.accenture_project.order.dtos;

import java.util.UUID;

public record ClientProducerDTO(UUID id,
                                String name,
                                String cellphone,
                                String email,
                                AddressProducerDTO address) {
}
