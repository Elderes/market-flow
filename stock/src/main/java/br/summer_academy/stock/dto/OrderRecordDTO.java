package br.summer_academy.stock.dto;

import java.util.List;

public record OrderRecordDTO(CostumerRecordDTO costumer, List<ProductRecordDTO> products) {

}
