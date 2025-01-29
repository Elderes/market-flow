package com.accenture_project.order.mappers;

import com.accenture_project.order.dtos.OrderDTO;
import com.accenture_project.order.models.OrderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class OrderMapper {

    private final ClientMapper clientMapper;

    public OrderModel toOrderModel(OrderDTO orderDTO) {
        var order = new OrderModel();

        order.setClient(clientMapper.toClientModel(orderDTO.client()));
        order.setOrderDateTime(LocalDateTime.now());

        return order;
    }
}