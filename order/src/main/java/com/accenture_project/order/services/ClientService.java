package com.accenture_project.order.services;

import com.accenture_project.order.exceptions.*;
import com.accenture_project.order.models.ClientModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final AddressService addressService;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public void validateClient(ClientModel client) {
        if (client.getName().isBlank()) {
            throw new InvalidClientException("O nome do cliente não pode ser vazio.");
        }

        if (client.getCellphone().isBlank()) {
            throw new InvalidClientException("O celular do cliente não pode ser vazio.");
        }

        if (!client.getCellphone().matches("\\d{11}")) {
            throw new InvalidClientException("O celular deve conter exatamente 11 números.");
        }

        if (client.getEmail().isBlank()) {
            throw new InvalidClientException("O e-mail do cliente não pode ser vazio.");
        }

        if (!client.getEmail().matches(EMAIL_REGEX)) {
            throw new InvalidClientException("O e-mail informado não é válido.");
        }

        addressService.validateAddress(client.getAddress());
    }
}
