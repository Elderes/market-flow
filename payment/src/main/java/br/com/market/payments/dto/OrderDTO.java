package br.com.market.payments.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderDTO(UUID id, ClientDTO client, LocalDateTime orderDateTime, BigDecimal totalPrice) {
}
