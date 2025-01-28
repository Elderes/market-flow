package br.com.market.payments.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDTO(UUID id, String name, Integer quantity, BigDecimal price){
}
