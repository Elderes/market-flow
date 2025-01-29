package br.summer_academy.stock.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderRecordDTO(UUID id, ClientRecordDTO client, List<ProductRecordDTO> products, LocalDateTime orderDateTime) {
}
