package br.summer_academy.stock.dto;

import java.util.UUID;

public record ClientRecordDTO(UUID id, String name, String cellphone, String email, AddressRecordDTO address) {

}
