package com.accenture_project.order.mappers;

import com.accenture_project.order.dtos.AddressDTO;
import com.accenture_project.order.dtos.ClientDTO;
import com.accenture_project.order.dtos.OrderDTO;
import com.accenture_project.order.dtos.ProductDTO;
import com.accenture_project.order.models.AddressModel;
import com.accenture_project.order.models.ClientModel;
import com.accenture_project.order.models.OrderModel;
import com.accenture_project.order.models.ProductModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

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
        order.setTotalPrice();

        return order;
    }






}