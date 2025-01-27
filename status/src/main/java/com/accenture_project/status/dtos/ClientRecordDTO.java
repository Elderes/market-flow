package com.accenture_project.status.dtos;

import java.util.UUID;

public record ClientRecordDTO(UUID id, String name, String cellphone, String email, AddressRecordDTO address) {

}
