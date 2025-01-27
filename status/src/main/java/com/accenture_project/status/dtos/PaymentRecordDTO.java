package com.accenture_project.status.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentRecordDTO(UUID id, BigDecimal value, LocalDateTime dateTime, String code) {

}
