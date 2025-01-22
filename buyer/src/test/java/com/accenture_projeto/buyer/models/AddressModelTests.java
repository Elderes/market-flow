package com.accenture_projeto.buyer.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AddressModelTests {
    @Test
    public void testCreateAddressModel() {
        var addressModel = new AddressModel();

        assertNull(addressModel.getId());
        assertNull(addressModel.getCountry());
        assertNull(addressModel.getState());
        assertNull(addressModel.getCity());
        assertNull(addressModel.getNeighborhood());
        assertNull(addressModel.getStreet());
        assertNull(addressModel.getNumber());
    }

    @Test
    public void testSettersAndGetters() {
        var addressModel = new AddressModel();

        addressModel.setCountry("country");
        addressModel.setState("state");
        addressModel.setCity("city");
        addressModel.setNeighborhood("neighborhood");
        addressModel.setStreet("street");
        addressModel.setNumber(0);

        assertEquals("country", addressModel.getCountry());
        assertEquals("state", addressModel.getState());
        assertEquals("city", addressModel.getCity());
        assertEquals("neighborhood", addressModel.getNeighborhood());
        assertEquals("street", addressModel.getStreet());
        assertEquals(0, addressModel.getNumber());

        assertNotEquals("country1", addressModel.getCountry());
        assertNotEquals("state1", addressModel.getState());
        assertNotEquals("city1", addressModel.getCity());
        assertNotEquals("neighborhood1", addressModel.getNeighborhood());
        assertNotEquals("street1", addressModel.getStreet());
        assertNotEquals(1, addressModel.getNumber());
    }
}
