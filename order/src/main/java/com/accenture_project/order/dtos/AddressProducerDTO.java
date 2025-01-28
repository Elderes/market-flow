package com.accenture_project.order.dtos;

import java.util.UUID;

public record AddressProducerDTO(UUID id,
                                 String country,
                                 String state,
                                 String city,
                                 String neighborhood,
                                 String street,
                                 Integer number) {
}
