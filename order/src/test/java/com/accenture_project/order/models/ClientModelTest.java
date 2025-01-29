package com.accenture_project.order.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ClientModelTest {

    private ClientModel client;
    private AddressModel address;

    @BeforeEach
    public void setUp() {
        address = new AddressModel();
        address.setId(UUID.randomUUID());
        address.setCountry("Brasil");
        address.setState("Paraíba");
        address.setCity("Esperança");
        address.setNeighborhood("Centro");
        address.setStreet("Rua x");
        address.setNumber(10);

        client = new ClientModel();
        client.setId(UUID.randomUUID());
        client.setName("Lucas");
        client.setCellphone("83991546906");
        client.setEmail("lucazmatehus14@gmail.com");
        client.setAddress(address);
    }

    @Test
    public void testClientModel_GettersAndSetters() {
        UUID id = UUID.randomUUID();
        String name = "Matheus";
        String cellphone = "83991546906";
        String email = "lucazmatehus14@gmail.com";

        client.setId(id);
        client.setName(name);
        client.setCellphone(cellphone);
        client.setEmail(email);

        assertEquals(id, client.getId());
        assertEquals(name, client.getName());
        assertEquals(cellphone, client.getCellphone());
        assertEquals(email, client.getEmail());
    }

    @Test
    public void testClientModel_AddressRelationship() {
        assertNotNull(client.getAddress());
        assertEquals(address, client.getAddress());
    }

    @Test
    public void testClientModel_NoArgsConstructor() {
        ClientModel emptyClient = new ClientModel();
        assertNull(emptyClient.getId());
        assertNull(emptyClient.getName());
        assertNull(emptyClient.getCellphone());
        assertNull(emptyClient.getEmail());
        assertNull(emptyClient.getAddress());
    }

    @Test
    public void testClientModel_AllArgsConstructor() {
        UUID id = UUID.randomUUID();
        String name = "Lima";
        String cellphone = "83991546906";
        String email = "lucazmatheus14@gmail.com";

        ClientModel newClient = new ClientModel(id, name, cellphone, email, address);

        assertEquals(id, newClient.getId());
        assertEquals(name, newClient.getName());
        assertEquals(cellphone, newClient.getCellphone());
        assertEquals(email, newClient.getEmail());
        assertEquals(address, newClient.getAddress());
    }
}