package com.accenture_project.order.mappers;

import com.accenture_project.order.dtos.ClientDTO;
import com.accenture_project.order.models.ClientModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ClientMapper {

    private final AddressMapper addressMapper;

    public ClientModel toClientModel(ClientDTO clientDTO) {
        var client = new ClientModel();

        client.setName(clientDTO.name());
        client.setCellphone(clientDTO.cellphone());
        client.setEmail(clientDTO.email());
        client.setAddress(addressMapper.toAddressModel(clientDTO.address()));

        return client;
    }

    public ClientDTO toClientDTO(ClientModel clientModel) {

        var address = addressMapper.toAddressDTO(clientModel.getAddress());

        return new ClientDTO(clientModel.getName(),
                clientModel.getCellphone(),
                clientModel.getEmail(),
                address);
    }
}
