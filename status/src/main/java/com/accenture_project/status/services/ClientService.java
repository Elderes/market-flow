package com.accenture_project.status.services;

import com.accenture_project.status.exceptions.NoClientException;
import com.accenture_project.status.models.ClientModel;
import com.accenture_project.status.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service class responsible for handling operations related to clients.
 *
 * - verifyClient: Verifies if the given client exists in the repository by its ID.
 * - getAllClients: Retrieves all clients from the repository. Throws NoClientException if no clients are found.
 * - getClient: Retrieves a specific client by its ID. Throws NoClientException if the client is not found.
 */

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientModel verifyClient(ClientModel client) {
        return clientRepository.findById(client.getId()).orElse(client);
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
}