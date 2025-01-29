package com.accenture_project.order.mappers;

import com.accenture_project.order.dtos.OrderDTO;
import com.accenture_project.order.models.OrderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/*
 * OrderUpdateMapper Class
 *
 * This class provides a method to update an OrderModel using data from an OrderDTO.
 * - toOrderModel: Updates an OrderModel with data from an OrderDTO, including updating the client information.
 */

@RequiredArgsConstructor
@Component
public class OrderUpdateMapper {

    private final ClientUpdateMapper clientUpdateMapper;

    public OrderModel toOrderModel(OrderModel orderModel, OrderDTO orderDTO) {

        orderModel.setClient(clientUpdateMapper.toClientModel(orderModel.getClient(), orderDTO));

        return orderModel;
    }
}
