package accenture_project.Order.dtos;

import org.hibernate.validator.constraints.NotBlank;

public record ProductDTO(@NotBlank String name,
                         Integer quantity){
}
