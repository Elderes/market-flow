package com.accenture_project.order.services;

import com.accenture_project.order.dtos.ClientDTO;
import com.accenture_project.order.exceptions.InvalidClientException;
import com.accenture_project.order.exceptions.NoClientException;
import com.accenture_project.order.mappers.ClientUpdateMapper;
import com.accenture_project.order.models.ClientModel;
import com.accenture_project.order.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private AddressService addressService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientUpdateMapper clientUpdateMapper;

    @InjectMocks
    private ClientService clientService;

    private ClientModel validClient;
    private ClientDTO clientDTO;

    @BeforeEach
    public void setUp() {
        validClient = new ClientModel();
        validClient.setName("Lucas Matheus Gomes de Lima");
        validClient.setCellphone("83991546906");
        validClient.setEmail("lucazmatehus14@gmail.com");

        clientDTO = new ClientDTO("Lucas Matheus Gomes de Lima",
                "83991546906",
                "lucazmatehus@gmail.com",
                null);
    }

    @Test
    public void testValidateClient_ValidClient_NoExceptionThrown() {
        assertDoesNotThrow(() -> clientService.validateClient(validClient));
        verify(addressService, times(1)).validateAddress(validClient.getAddress());
    }

    @Test
    public void testValidateClient_EmptyName_ThrowsException() {
        validClient.setName("");
        assertThrows(InvalidClientException.class, () -> clientService.validateClient(validClient));
    }

    @Test
    public void testValidateClient_EmptyCellphone_ThrowsException() {
        validClient.setCellphone("");
        assertThrows(InvalidClientException.class, () -> clientService.validateClient(validClient));
    }

    @Test
    public void testValidateClient_InvalidCellphone_ThrowsException() {
        validClient.setCellphone("123");
        assertThrows(InvalidClientException.class, () -> clientService.validateClient(validClient));
    }

    @Test
    public void testValidateClient_EmptyEmail_ThrowsException() {
        validClient.setEmail("");
        assertThrows(InvalidClientException.class, () -> clientService.validateClient(validClient));
    }

    @Test
    public void testValidateClient_InvalidEmail_ThrowsException() {
        validClient.setEmail("invalid-email");
        assertThrows(InvalidClientException.class, () -> clientService.validateClient(validClient));
    }

    @Test
    public void testGetAllClients_NoClients_ThrowsException() {
        when(clientRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(NoClientException.class, () -> clientService.getAllClients());
    }

    @Test
    public void testGetAllClients_WithClients_ReturnsList() {
        when(clientRepository.findAll()).thenReturn(List.of(validClient));
        List<ClientModel> clients = clientService.getAllClients();
        assertFalse(clients.isEmpty());
        assertEquals(1, clients.size());
    }

    @Test
    public void testGetClient_NotFound_ThrowsException() {
        UUID id = UUID.randomUUID();
        when(clientRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NoClientException.class, () -> clientService.getClient(id));
    }

    @Test
    public void testGetClient_Found_ReturnsClient() {
        UUID id = UUID.randomUUID();
        when(clientRepository.findById(id)).thenReturn(Optional.of(validClient));
        ClientModel client = clientService.getClient(id);
        assertNotNull(client);
        assertEquals(validClient, client);
    }

    @Test
    public void testUpdateClient_NotFound_ThrowsException() {
        UUID id = UUID.randomUUID();
        when(clientRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NoClientException.class, () -> clientService.updateClient(id, clientDTO));
    }

    @Test
    public void testUpdateClient_Found_UpdatesClient() {
        UUID id = UUID.randomUUID();
        when(clientRepository.findById(id)).thenReturn(Optional.of(validClient));
        when(clientUpdateMapper.toClientModel(validClient, clientDTO)).thenReturn(validClient);

        clientService.updateClient(id, clientDTO);

        verify(clientRepository, times(1)).save(validClient);
    }
}