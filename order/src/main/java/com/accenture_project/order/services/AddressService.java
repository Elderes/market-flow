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

/*
 * AddressService Class
 *
 * This class provides services related to address operations. It validates addresses,
 * retrieves all addresses, fetches an address by ID, and updates an address.
 * It uses the AddressRepository to interact with the database and AddressUpdateMapper
 * to update address information.
 *
 * Key methods:
 * - validateAddress(AddressModel address): Validates the fields of an address.
 * - getAllAddresses(): Retrieves all addresses and throws an exception if none are found.
 * - getAddress(UUID id): Fetches an address by its ID and throws an exception if not found.
 * - updateAddress(UUID id, AddressDTO addressDTO): Updates an existing address using data from the provided AddressDTO.
 */

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
