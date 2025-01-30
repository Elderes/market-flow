package br.com.accenture_project.order.mappers;

import br.com.accenture_project.order.dtos.OrderProducerDTO;
import br.com.accenture_project.order.dtos.ProductDTO;
import br.com.accenture_project.order.models.OrderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/*
 * OrderProducerMapper Class
 *
 * This class provides a method to map an OrderModel to an OrderProducerDTO.
 * - toOrderProducerDTO: Converts an OrderModel and a list of ProductDTOs to an OrderProducerDTO.
 */


@RequiredArgsConstructor
@Component
public class OrderProducerMapper {

    private final ClientMapper clientMapper;

    public OrderProducerDTO toOrderProducerDTO(OrderModel orderModel, List<ProductDTO> productsDTO) {

        var clientDTO = clientMapper.toClientDTO(orderModel.getClient());

        return new OrderProducerDTO(orderModel.getId(),
                clientDTO,
                productsDTO,
                orderModel.getOrderDateTime());
    }
}
