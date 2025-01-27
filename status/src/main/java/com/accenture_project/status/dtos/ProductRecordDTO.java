package com.accenture_project.status.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRecordDTO(UUID id, String name, Integer quantity, BigDecimal price) {

}
