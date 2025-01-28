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
    private final ProductsMapper productsMapper;

    public OrderModel toOrderModel(OrderDTO orderDTO) {
        var order = new OrderModel();

        order.setClient(clientMapper.toClientModel(orderDTO.client()));

        var products = productsMapper.toProductModels(orderDTO.products());

        products.forEach(product -> product.setOrder(order));
        order.setProducts(products);
        order.setOrderDateTime(LocalDateTime.now());

        return order;
    }






}