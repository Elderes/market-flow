package com.accenture_project.order.mappers;

import com.accenture_project.order.dtos.AddressProducerDTO;
import com.accenture_project.order.models.AddressModel;
import org.springframework.stereotype.Component;

@Component
public class AddressProducerMapper {

    public AddressProducerDTO toAddressProducerDTO(AddressModel addressModel) {
        return new AddressProducerDTO(addressModel.getId(),
                addressModel.getCountry(),
                addressModel.getState(),
                addressModel.getCity(),
                addressModel.getNeighborhood(),
                addressModel.getStreet(),
                addressModel.getNumber());
    }
}
