package com.accenture_project.order.services;

import com.accenture_project.order.exceptions.InvalidAddressException;
import com.accenture_project.order.models.AddressModel;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    public void validateAddress(AddressModel address) {
        if (address.getCountry().isBlank()) {
            throw new InvalidAddressException("O campo 'país' não pode ser vazio.");
        }

        if (address.getState().isBlank()) {
            throw new InvalidAddressException("O campo 'estado' não pode ser vazio.");
        }

        if (address.getCity().isBlank()) {
            throw new InvalidAddressException("O campo 'cidade' não pode ser vazio.");
        }

        if (address.getNeighborhood().isBlank()) {
            throw new InvalidAddressException("O campo 'bairro' não pode ser vazio.");
        }

        if (address.getStreet().isBlank()) {
            throw new InvalidAddressException("O campo 'rua' não pode ser vazio.");
        }

        if (address.getNumber() < 1) {
            throw new InvalidAddressException("O campo 'número da casa' não pode ser menos que 1.");
        }
    }
}
