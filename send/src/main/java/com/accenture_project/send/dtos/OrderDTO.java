package com.accenture_project.send.dtos;

import java.util.List;

/**
 * Data Transfer Object (DTO) for representing an order.
 * This class contains the client details and the products involved in the order.
 * It is used to transfer order data between layers or services in the application.
 */

public record OrderDTO(ClientDTO client,
                       List<ProductDTO> products) {
}
