package br.summer_academy.stock.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class ClientTest {

    @Test
    void testClientModel() {
        UUID id = UUID.randomUUID();
        String name = "John Doe";
        String cellphone = "123456789";
        String email = "john@example.com";
        Address address = new Address(UUID.randomUUID(), "USA", "California", "Los Angeles", "Downtown", "Main St", "123");

        Client client = new Client();
        client.setId(id);
        client.setName(name);
        client.setCellphone(cellphone);
        client.setEmail(email);
        client.setAddress(address);

        assertEquals(id, client.getId());
        assertEquals(name, client.getName());
        assertEquals(cellphone, client.getCellphone());
        assertEquals(email, client.getEmail());
        assertEquals(address, client.getAddress());
    }

    @Test
    void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        String name = "Jane Doe";
        String cellphone = "987654321";
        String email = "jane@example.com";
        Address address = new Address(UUID.randomUUID(), "Canada", "Ontario", "Toronto", "Midtown", "Queen St", "456");

        Client client = new Client(id, name, cellphone, email, address);

        assertEquals(id, client.getId());
        assertEquals(name, client.getName());
        assertEquals(cellphone, client.getCellphone());
        assertEquals(email, client.getEmail());
        assertEquals(address, client.getAddress());
    }

    @Test
    void testNoArgsConstructor() {
        Client client = new Client();
        assertNotNull(client);
    }
}
