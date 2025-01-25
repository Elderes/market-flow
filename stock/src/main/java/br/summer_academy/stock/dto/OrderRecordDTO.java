package br.summer_academy.stock.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderRecordDTO(ClientRecordDTO client, List<ProductRecordDTO> products, LocalDateTime orderDateTime) {
}
