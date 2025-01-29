package com.accenture_project.order.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderProducerDTO(UUID id,
                               ClientDTO client,
                               List<ProductDTO> products,
                               LocalDateTime orderDateTime) {
}
