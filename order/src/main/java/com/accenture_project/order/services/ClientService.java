package com.accenture_project.order.services;

import com.accenture_project.order.dtos.ClientDTO;
import com.accenture_project.order.exceptions.*;
import com.accenture_project.order.mappers.ClientUpdateMapper;
import com.accenture_project.order.models.ClientModel;
import com.accenture_project.order.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final AddressService addressService;
    private final ClientRepository clientRepository;
    private final ClientUpdateMapper clientUpdateMapper;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public void validateClient(ClientModel client) {
        if (client.getName().isBlank()) {
            throw new InvalidClientException("The 'name' field cannot be empty.");
        }

        if (client.getCellphone().isBlank()) {
            throw new InvalidClientException("The 'cellphone' field cannot be empty.");
        }

        if (!client.getCellphone().matches("\\d{11}")) {
            throw new InvalidClientException("The 'cellphone' field must have exactly 11 numbers.");
        }

        if (client.getEmail().isBlank()) {
            throw new InvalidClientException("The 'cellphone' field cannot be empty.");
        }

        if (!client.getEmail().matches(EMAIL_REGEX)) {
            throw new InvalidClientException("The email provided is not valid.");
        }

        addressService.validateAddress(client.getAddress());
    }

    public List<ClientModel> getAllClients() {
        var clients = clientRepository.findAll();

        if (clients.isEmpty()) {
            throw new NoClientException("There are no client");
        }

        return clients;
    }

    public ClientModel getClient(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NoClientException("Client not found with id:" + id));
    }

    public void deleteById(UUID id) {
        if (getClient(id) == null) {
            throw new NoClientException("Client not found with id:" + id);
        }
        clientRepository.deleteById(id);
    }

    public void updateClient(UUID id, ClientDTO clientDTO) {
        var client = clientRepository.findById(id)
                .orElseThrow(() -> new NoClientException("Client not found"));

        client = clientUpdateMapper.toClientModel(client, clientDTO);

        clientRepository.save(client);
    }
}
