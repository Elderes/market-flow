package br.com.market.payments.mapper;

import br.com.market.payments.dto.OrderDTO;
import br.com.market.payments.dto.ProductDTO;
import br.com.market.payments.exception.NoClientException;
import br.com.market.payments.model.OrderModel;
import br.com.market.payments.model.ProductModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class OrderMapper {

    private final ClientMapper clientMapper;

    public OrderModel toOrderModel(OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new NoClientException("No client found");
        }

        var order = new OrderModel();

        order.setId(orderDTO.id());
        order.setClient(clientMapper.toClientModel(orderDTO.client()));

        order.setOrderDateTime(orderDTO.orderDateTime());

        return order;
    }
}
