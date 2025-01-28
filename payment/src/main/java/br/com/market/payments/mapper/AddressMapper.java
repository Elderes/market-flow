package br.com.market.payments.mapper;

import br.com.market.payments.dto.AddressDTO;
import br.com.market.payments.exception.NoAddressException;
import br.com.market.payments.model.AddressModel;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public AddressModel toAddressModel(AddressDTO addressDTO) {
        if (addressDTO == null) {
            throw new NoAddressException("No address found");
        }

        var address = new AddressModel();

        address.setId(addressDTO.id());
        address.setCountry(addressDTO.country());
        address.setState(addressDTO.state());
        address.setCity(addressDTO.city());
        address.setStreet(addressDTO.street());
        address.setNeighborhood(addressDTO.neighborhood());
        address.setNumber(addressDTO.number());

        return address;
    }
}
