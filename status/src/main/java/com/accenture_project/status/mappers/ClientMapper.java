package com.accenture_project.status.mappers;

import com.accenture_project.status.dtos.ClientDTO;
import com.accenture_project.status.exceptions.NoClientException;
import com.accenture_project.status.models.ClientModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ClientMapper {

    private final AddressMapper addressMapper;

    public ClientModel toClientModel(ClientDTO clientDTO) {
        if (clientDTO == null) {
            throw new NoClientException("No client found");
        }

        var client = new ClientModel();

        client.setId(clientDTO.id());
        client.setName(clientDTO.name());
        client.setCellphone(clientDTO.cellphone());
        client.setEmail(clientDTO.email());

        client.setAddress(addressMapper.toAddressModel(clientDTO.address()));

        return client;
    }
}
