package accenture_project.Order.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

public record ClientDTO(@NotBlank String name,
                        @NotBlank @Pattern(regexp = "\\d{2}\\d{5}\\d{4}",
                                message = "O número de celular deve estar no formato XXXXXXXXXXX") String cellphone,
                        @NotBlank @Email String email,
                        AddressDTO address) {
}
