package br.com.accenture_project.order.mappers;

import br.com.accenture_project.order.dtos.AddressDTO;
import br.com.accenture_project.order.dtos.ClientDTO;
import br.com.accenture_project.order.dtos.OrderDTO;
import br.com.accenture_project.order.models.AddressModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/*
 * AddressUpdateMapper Class
 *
 * This class provides methods to update an AddressModel using data from different DTOs:
 * - toAddressModel (AddressDTO): Updates an AddressModel with data from AddressDTO.
 * - toAddressModel (ClientDTO): Updates an AddressModel with data from ClientDTO.
 * - toAddressModel (OrderDTO): Updates an AddressModel with data from OrderDTO.
 */

@RequiredArgsConstructor
@Component
public class AddressUpdateMapper {

    public AddressModel toAddressModel(AddressModel addressModel, AddressDTO addressDTO) {
        addressModel.setCountry(addressDTO.country());
        addressModel.setState(addressDTO.state());
        addressModel.setCity(addressDTO.city());
        addressModel.setNeighborhood(addressDTO.neighborhood());
        addressModel.setStreet(addressDTO.street());
        addressModel.setNumber(addressDTO.number());


        return addressModel;
    }

    public AddressModel toAddressModel(AddressModel addressModel, ClientDTO clientDTO) {
        addressModel.setCountry(clientDTO.address().country());
        addressModel.setState(clientDTO.address().state());
        addressModel.setCity(clientDTO.address().city());
        addressModel.setNeighborhood(clientDTO.address().neighborhood());
        addressModel.setStreet(clientDTO.address().street());
        addressModel.setNumber(clientDTO.address().number());


        return addressModel;
    }

    public AddressModel toAddressModel(AddressModel addressModel, OrderDTO orderDTO) {
        addressModel.setCountry(orderDTO.client().address().country());
        addressModel.setState(orderDTO.client().address().state());
        addressModel.setCity(orderDTO.client().address().city());
        addressModel.setNeighborhood(orderDTO.client().address().neighborhood());
        addressModel.setStreet(orderDTO.client().address().street());
        addressModel.setNumber(orderDTO.client().address().number());

        return addressModel;
    }
}
