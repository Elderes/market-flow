package accenture_project.Order.dtos;

import java.util.List;

public record OrderDTO(ClientDTO client,
                       List<ProductDTO> products) {
}
