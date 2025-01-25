package br.summer_academy.stock.dto;

import java.util.UUID;

public record AddressRecordDTO(UUID id, String country, String state, String city, String neighborhood, String street, String number) {

}