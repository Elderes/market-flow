package br.com.accenture_project.order.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AddressModelTest {

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
        address.setNumber(100);
    }

    @Test
    public void testAddressModel_GettersAndSetters() {
        UUID id = UUID.randomUUID();
        String country = "Brasil";
        String state = "Paraíba";
        String city = "Campina Grande";
        String neighborhood = "Centro";
        String street = "Rua y";
        Integer number = 200;

        address.setId(id);
        address.setCountry(country);
        address.setState(state);
        address.setCity(city);
        address.setNeighborhood(neighborhood);
        address.setStreet(street);
        address.setNumber(number);

        assertEquals(id, address.getId());
        assertEquals(country, address.getCountry());
        assertEquals(state, address.getState());
        assertEquals(city, address.getCity());
        assertEquals(neighborhood, address.getNeighborhood());
        assertEquals(street, address.getStreet());
        assertEquals(number, address.getNumber());
    }

    @Test
    public void testAddressModel_NoArgsConstructor() {
        AddressModel emptyAddress = new AddressModel();
        assertNull(emptyAddress.getId());
        assertNull(emptyAddress.getCountry());
        assertNull(emptyAddress.getState());
        assertNull(emptyAddress.getCity());
        assertNull(emptyAddress.getNeighborhood());
        assertNull(emptyAddress.getStreet());
        assertNull(emptyAddress.getNumber());
    }

    @Test
    public void testAddressModel_AllArgsConstructor() {
        UUID id = UUID.randomUUID();
        String country = "Brasil";
        String state = "Paraíba";
        String city = "João Pessoa";
        String neighborhood = "Centro";
        String street = "Rua z";
        Integer number = 300;

        AddressModel newAddress = new AddressModel(id, country, state, city, neighborhood, street, number);

        assertEquals(id, newAddress.getId());
        assertEquals(country, newAddress.getCountry());
        assertEquals(state, newAddress.getState());
        assertEquals(city, newAddress.getCity());
        assertEquals(neighborhood, newAddress.getNeighborhood());
        assertEquals(street, newAddress.getStreet());
        assertEquals(number, newAddress.getNumber());
    }
}