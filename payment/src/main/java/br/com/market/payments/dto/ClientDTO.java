package br.com.market.payments.dto;

public record ClientDTO(String name,
                        String cellphone,
                        String email,
                        AddressDTO address) {
}
