package com.accenture_project.order.dtos;

public record ClientDTO(String name,
                        String cellphone,
                        String email,
                        AddressDTO address) {
}
