package com.accenture_project.status.dtos;

import java.util.UUID;

public record AddressRecordDTO(UUID id, String country, String state, String city, String neighborhood, String street, Integer number) {

}