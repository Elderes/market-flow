package com.accenture_project.order.services;

import com.accenture_project.order.dtos.AddressDTO;
import com.accenture_project.order.exceptions.InvalidAddressException;
import com.accenture_project.order.exceptions.NoAddressException;
import com.accenture_project.order.mappers.AddressUpdateMapper;
import com.accenture_project.order.models.AddressModel;
import com.accenture_project.order.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepository addressRepository;

    private final AddressUpdateMapper addressUpdateMapper;

    public void validateAddress(AddressModel address) {
        if (address.getCountry().isBlank()) {
            throw new InvalidAddressException("The 'country' field cannot be empty.");
        }

        if (address.getState().isBlank()) {
            throw new InvalidAddressException("The 'state' field cannot be empty.");
        }

        if (address.getCity().isBlank()) {
            throw new InvalidAddressException("The 'city' field cannot be empty.");
        }

        if (address.getNeighborhood().isBlank()) {
            throw new InvalidAddressException("The 'neighborhood' field cannot be empty.");
        }

        if (address.getStreet().isBlank()) {
            throw new InvalidAddressException("The 'street' field cannot be empty.");
        }

        if (address.getNumber() < 1) {
            throw new InvalidAddressException("The 'house number' field cannot be less than 1.");
        }
    }

    public List<AddressModel> getAllAddresses() {
        var addresses = addressRepository.findAll();

        if (addresses.isEmpty()) {
            throw new NoAddressException("There are no addresses");
        }

        return addresses;
    }

    public AddressModel getAddress(UUID id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new NoAddressException("Address not found with id:" + id));
    }

    public void updateAddress(UUID id, AddressDTO addressDTO) {
        var address = addressRepository.findById(id)
                .orElseThrow(() -> new NoAddressException("Address not found"));

        address = addressUpdateMapper.toAddressModel(address, addressDTO);

        addressRepository.save(address);
    }
}
