package com.accenture_project.order.mappers;

import com.accenture_project.order.dtos.OrderProducerDTO;
import com.accenture_project.order.models.OrderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderProducerMapper {

    private final ClientProducerMapper clientProducerMapper;
    private final ProductsProducerMapper productsProducerMapper;

    public OrderProducerDTO toOrderProducerDTO(OrderModel orderModel) {
        var clientProducerDTO = clientProducerMapper.toClientProducerDTO(orderModel.getClient());
        var productsProducerDTO = productsProducerMapper.toProductsProducerDTO(orderModel.getProducts());

        return new OrderProducerDTO(orderModel.getId(),
                clientProducerDTO,
                productsProducerDTO,
                orderModel.getOrderDateTime(),
                orderModel.getTotalPrice());
    }
}
