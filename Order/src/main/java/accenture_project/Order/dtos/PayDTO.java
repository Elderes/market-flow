package accenture_project.Order.dtos;

import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

public record PayDTO(@NotBlank String code,
                     BigDecimal value) {
}
