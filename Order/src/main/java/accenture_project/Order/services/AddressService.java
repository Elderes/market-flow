package accenture_project.Order.services;

import accenture_project.Order.models.AddressModel;
import accenture_project.Order.repositories.AddressRepository;
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
