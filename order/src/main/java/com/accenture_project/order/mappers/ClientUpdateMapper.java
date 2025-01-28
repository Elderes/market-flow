package com.accenture_project.order.mappers;

import com.accenture_project.order.dtos.ClientDTO;
import com.accenture_project.order.dtos.OrderDTO;
import com.accenture_project.order.models.ClientModel;
import com.accenture_project.order.models.OrderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

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
