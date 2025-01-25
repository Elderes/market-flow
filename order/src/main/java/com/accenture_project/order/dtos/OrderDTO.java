package com.accenture_project.order.dtos;

import java.util.List;

public record OrderDTO(ClientDTO client,
                       List<ProductDTO> products) {
}
