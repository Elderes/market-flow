package com.accenture_project.status.mappers;

import com.accenture_project.status.dtos.OrderDTO;
import com.accenture_project.status.exceptions.NoClientException;
import com.accenture_project.status.models.OrderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderMapper {

    private final ClientMapper clientMapper;
    private final ProductsMapper productsMapper;

    public OrderModel toOrderModel(OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new NoClientException("No client found");
        }

        var order = new OrderModel();

        order.setId(orderDTO.id());
        order.setClient(clientMapper.toClientModel(orderDTO.client()));
        order.setProducts(productsMapper.toProductsModel(orderDTO.products()));
        order.setOrderDateTime(orderDTO.orderDateTime());

        return order;
    }
}
