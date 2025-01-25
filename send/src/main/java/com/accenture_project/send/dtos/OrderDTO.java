package com.accenture_project.send.dtos;

import java.util.List;

public record OrderDTO(ClientDTO client,
                       List<ProductDTO> products) {
}
