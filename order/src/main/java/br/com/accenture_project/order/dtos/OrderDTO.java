package br.com.accenture_project.order.dtos;

import java.util.List;

/*
 * OrderDTO Class
 *
 * A DTO for representing an order, including the client and the list of products.
 */

public record OrderDTO(ClientDTO client,
                       List<ProductDTO> products) {
}
