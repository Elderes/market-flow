package br.com.accenture_project.stock.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRecordDTO(UUID id, String name, Integer quantity, BigDecimal unitPrice) {

}
