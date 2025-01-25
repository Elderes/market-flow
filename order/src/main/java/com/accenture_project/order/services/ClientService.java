package com.accenture_project.order.services;

import com.accenture_project.order.models.ClientModel;
import com.accenture_project.order.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientModel saveClient(ClientModel client) {
        return clientRepository.save(client);
    }
}
