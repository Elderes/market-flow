package br.summer_academy.stock.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.UUID;

class AddressRecordDTOTest {

    @Test
    void testAddressRecordDTO() {
        UUID id = UUID.randomUUID();
        String country = "Brasil";
        String state = "SP";
        String city = "São Paulo";
        String neighborhood = "Centro";
        String street = "Avenida Paulista";
        String number = "1000";

        AddressRecordDTO address = new AddressRecordDTO(id, country, state, city, neighborhood, street, number);

        assertEquals(id, address.id());
        assertEquals(country, address.country());
        assertEquals(state, address.state());
        assertEquals(city, address.city());
        assertEquals(neighborhood, address.neighborhood());
        assertEquals(street, address.street());
        assertEquals(number, address.number());
    }

    @Test
    void testAddressRecordDTONullValues() {
        AddressRecordDTO address = new AddressRecordDTO(null, null, null, null, null, null, null);
        assertNull(address.id());
        assertNull(address.country());
        assertNull(address.state());
        assertNull(address.city());
        assertNull(address.neighborhood());
        assertNull(address.street());
        assertNull(address.number());
    }
}
