package com.accenture_project.order.mappers;

import com.accenture_project.order.dtos.ClientProducerDTO;
import com.accenture_project.order.models.ClientModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ClientProducerMapper {

    private final AddressProducerMapper addressProducerMapper;

    public ClientProducerDTO toClientProducerDTO(ClientModel clientModel) {
        var clientProducerDTO = addressProducerMapper.toAddressProducerDTO(clientModel.getAddress());

        return new ClientProducerDTO(clientModel.getId(),
                clientModel.getName(),
                clientModel.getCellphone(),
                clientModel.getEmail(),
                clientProducerDTO);

    }
}
