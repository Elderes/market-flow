package br.com.market.payments.dto;

import java.util.UUID;

public record ClientDTO(UUID id, String name,
                        String cellphone,
                        String email,
                        AddressDTO address) {
}
