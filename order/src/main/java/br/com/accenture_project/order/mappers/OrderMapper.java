package br.com.accenture_project.order.mappers;

import br.com.accenture_project.order.dtos.OrderDTO;
import br.com.accenture_project.order.models.OrderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/*
 * OrderMapper Class
 *
 * This class provides a method to map an OrderDTO to an OrderModel.
 * - toOrderModel: Converts an OrderDTO to an OrderModel, including setting the current order date/time.
 */


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