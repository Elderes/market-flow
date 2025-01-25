package accenture_project.Order.mappers;

import accenture_project.Order.dtos.PayDTO;
import accenture_project.Order.models.PayModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PayMapper {
    public PayModel toPayModel(PayDTO payDTO) {
        return new PayModel(payDTO.code(), payDTO.value(), LocalDateTime.now());
    }
}
