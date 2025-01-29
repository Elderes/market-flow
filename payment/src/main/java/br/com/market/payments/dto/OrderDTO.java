package br.com.market.payments.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderDTO(UUID id,
                       ClientDTO client,
                       LocalDateTime orderDateTime,
                       List<ProductDTO> products) {
}
