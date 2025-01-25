package com.accenture_project.send.services;

import com.accenture_project.send.models.AddressModel;
import com.accenture_project.send.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressModel verifyAddress(AddressModel address) {
        return addressRepository.findById(address.getId()).orElse(address);
    }
}
