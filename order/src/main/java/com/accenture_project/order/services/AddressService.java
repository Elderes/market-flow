package com.accenture_project.order.services;

import com.accenture_project.order.models.AddressModel;
import com.accenture_project.order.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressModel saveAddress(AddressModel address) {
        return addressRepository.save(address);
    }
}
