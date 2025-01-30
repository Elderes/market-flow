package br.com.accenture_project.stock.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

    @Test
    public void testAddressGettersAndSetters() {
        // Cria um objeto Address
        Address address = new Address();
        address.setId(UUID.randomUUID());
        address.setCountry("Brasil");
        address.setState("São Paulo");
        address.setCity("São Paulo");
        address.setNeighborhood("Centro");
        address.setStreet("Rua Augusta");
        address.setNumber("100");

        // Verifica os valores
        assertNotNull(address.getId());
        assertEquals("Brasil", address.getCountry());
        assertEquals("São Paulo", address.getState());
        assertEquals("São Paulo", address.getCity());
        assertEquals("Centro", address.getNeighborhood());
        assertEquals("Rua Augusta", address.getStreet());
        assertEquals("100", address.getNumber());
    }

    @Test
    public void testAddressAllArgsConstructor() {
        // Cria um objeto Address usando o construtor com todos os argumentos
        UUID id = UUID.randomUUID();
        Address address = new Address(id, "Brasil", "São Paulo", "São Paulo", "Centro", "Rua Augusta", "100");

        // Verifica os valores
        assertEquals(id, address.getId());
        assertEquals("Brasil", address.getCountry());
        assertEquals("São Paulo", address.getState());
        assertEquals("São Paulo", address.getCity());
        assertEquals("Centro", address.getNeighborhood());
        assertEquals("Rua Augusta", address.getStreet());
        assertEquals("100", address.getNumber());
    }

    @Test
    public void testAddressNoArgsConstructor() {
        // Cria um objeto Address usando o construtor sem argumentos
        Address address = new Address();

        // Verifica se os campos são nulos
        assertNull(address.getId());
        assertNull(address.getCountry());
        assertNull(address.getState());
        assertNull(address.getCity());
        assertNull(address.getNeighborhood());
        assertNull(address.getStreet());
        assertNull(address.getNumber());
    }
}