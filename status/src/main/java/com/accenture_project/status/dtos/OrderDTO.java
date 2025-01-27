package com.accenture_project.status.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderDTO(UUID id,
                       ClientDTO client,
                       List<ProductDTO> products,
                       LocalDateTime orderDateTime,
                       BigDecimal totalPrice) {
}
