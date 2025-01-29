package com.accenture_project.order.mappers;

import com.accenture_project.order.dtos.ClientDTO;
import com.accenture_project.order.dtos.OrderDTO;
import com.accenture_project.order.models.ClientModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/*
 * ClientUpdateMapper Class
 *
 * This class provides methods to update a ClientModel using data from different DTOs:
 * - toClientModel (ClientDTO): Updates a ClientModel with data from ClientDTO.
 * - toClientModel (OrderDTO): Updates a ClientModel with data from OrderDTO.
 */

@RequiredArgsConstructor
@Component
public class ClientUpdateMapper {

    private final AddressUpdateMapper addressUpdateMapper;

    public ClientModel toClientModel(ClientModel clientModel, ClientDTO clientDTO) {

        clientModel.setName(clientDTO.name());
        clientModel.setCellphone(clientDTO.cellphone());
        clientModel.setEmail(clientDTO.email());

        clientModel.setAddress(addressUpdateMapper.toAddressModel(clientModel.getAddress(), clientDTO));

        return clientModel;
    }

    public ClientModel toClientModel(ClientModel clientModel, OrderDTO orderDTO) {

        clientModel.setName(orderDTO.client().name());
        clientModel.setCellphone(orderDTO.client().cellphone());
        clientModel.setEmail(orderDTO.client().email());

        addressUpdateMapper.toAddressModel(clientModel.getAddress(), orderDTO);

        return clientModel;
    }
}
