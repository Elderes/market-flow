package com.accenture_project.status.services;

import com.accenture_project.status.exceptions.NoAddressException;
import com.accenture_project.status.models.AddressModel;
import com.accenture_project.status.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service class responsible for handling operations related to addresses.
 *
 * - verifyAddress: Verifies if the given address exists in the repository by its ID.
 * - getAllAddresses: Retrieves all addresses from the repository. Throws NoAddressException if no addresses are found.
 * - getAddress: Retrieves a specific address by its ID. Throws NoAddressException if the address is not found.
 */

@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressModel verifyAddress(AddressModel address) {
        return addressRepository.findById(address.getId()).orElse(address);
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
}