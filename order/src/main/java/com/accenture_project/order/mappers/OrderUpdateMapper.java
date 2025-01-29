package com.accenture_project.order.mappers;

import com.accenture_project.order.dtos.OrderDTO;
import com.accenture_project.order.models.OrderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderUpdateMapper {

    private final ClientUpdateMapper clientUpdateMapper;

    public OrderModel toOrderModel(OrderModel orderModel, OrderDTO orderDTO) {

        orderModel.setClient(clientUpdateMapper.toClientModel(orderModel.getClient(), orderDTO));

        return orderModel;
    }
}
