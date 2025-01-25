package com.accenture_project.send.services;

import com.accenture_project.send.models.ClientModel;
import com.accenture_project.send.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientModel verifyClient(ClientModel client) {
        return clientRepository.findById(client.getId()).orElse(client);
    }
}
