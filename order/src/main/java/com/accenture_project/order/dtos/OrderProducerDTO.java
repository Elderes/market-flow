package com.accenture_project.order.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderProducerDTO(UUID id,
                               ClientProducerDTO client,
                               List<ProductProducerDTO> products,
                               LocalDateTime orderDateTime,
                               BigDecimal totalPrice) {
}
