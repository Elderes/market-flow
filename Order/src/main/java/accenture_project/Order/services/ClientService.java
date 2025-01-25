package accenture_project.Order.services;

import accenture_project.Order.models.ClientModel;
import accenture_project.Order.repositories.ClientRepository;
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
