package com.accenture_project.order.mappers;

import com.accenture_project.order.dtos.AddressDTO;
import com.accenture_project.order.models.AddressModel;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public AddressModel toAddressModel(AddressDTO addressDTO) {
        var address = new AddressModel();

        address.setCountry(addressDTO.country());
        address.setState(addressDTO.state());
        address.setCity(addressDTO.city());
        address.setNeighborhood(addressDTO.neighborhood());
        address.setStreet(addressDTO.street());
        address.setNumber(addressDTO.number());

        return address;
    }

    public AddressDTO toAddressDTO(AddressModel addressModel) {
        return new AddressDTO(addressModel.getCountry(),
                addressModel.getState(),
                addressModel.getCity(),
                addressModel.getNeighborhood(),
                addressModel.getStreet(),
                addressModel.getNumber());
    }
}
