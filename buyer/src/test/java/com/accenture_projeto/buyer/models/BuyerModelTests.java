package com.accenture_projeto.buyer.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BuyerModelTests {

    @Test
    public void testCreateBuyerModel() {
        var buyerModel = new BuyerModel();

        assertNull(buyerModel.getId());
        assertNull(buyerModel.getName());
        assertNull(buyerModel.getEmail());
        assertNull(buyerModel.getAddress());
        assertNull(buyerModel.getCellphone());
    }

    @Test
    public void testSettersAndGetters() {
        var buyerModel = new BuyerModel();

        buyerModel.setName("Test");
        buyerModel.setEmail("Test@test.com");
        buyerModel.setCellphone("00123456789");

        assertEquals("Test", buyerModel.getName());
        assertEquals("Test@test.com", buyerModel.getEmail());
        assertEquals("00123456789", buyerModel.getCellphone());

        assertNotEquals("Test1", buyerModel.getName());
        assertNotEquals("Test1@test.com", buyerModel.getEmail());
        assertNotEquals("001234567891", buyerModel.getCellphone());
    }
}
