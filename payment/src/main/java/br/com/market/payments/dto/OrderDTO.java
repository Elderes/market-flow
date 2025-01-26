package br.com.market.payments.dto;

import java.util.List;

public record OrderDTO(ClientDTO client,
                       List<ProductDTO> products) {
}
