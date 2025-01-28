package com.accenture_project.order.mappers;

import com.accenture_project.order.dtos.OrderDTO;
import com.accenture_project.order.models.OrderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class OrderUpdateMapper {

    private final ClientUpdateMapper clientUpdateMapper;
    private final ProductsUpdateMapper productsUpdateMapper;

    public OrderModel toOrderModel(OrderModel orderModel, OrderDTO orderDTO) {

        orderModel.setClient(clientUpdateMapper.toClientModel(orderModel.getClient(), orderDTO));
        orderModel.setProducts(productsUpdateMapper.toProductsModel(orderModel.getProducts(), orderDTO));

        return orderModel;
    }
}
