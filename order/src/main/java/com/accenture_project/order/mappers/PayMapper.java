package com.accenture_project.order.mappers;

import com.accenture_project.order.dtos.PayDTO;
import com.accenture_project.order.models.PayModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PayMapper {
    public PayModel toPayModel(PayDTO payDTO) {
        return new PayModel(payDTO.code(), payDTO.value(), LocalDateTime.now());
    }
}
