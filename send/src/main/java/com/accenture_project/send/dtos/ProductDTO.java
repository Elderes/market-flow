package com.accenture_project.send.dtos;

/**
 * Data Transfer Object (DTO) for representing a product.
 * This class contains information about a product, including its name and quantity.
 * It is used to transfer product data between layers or services in the application.
 */

public record ProductDTO(String name,
                         Integer quantity){
}
