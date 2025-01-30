package br.com.accenture_project.stock.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BadProductTest {

    @Test
    void testBadProductModel() {
        String name = "Product A";
        Integer quantity = 10;

        BadProduct badProduct = new BadProduct();
        badProduct.setName(name);
        badProduct.setQuantity(quantity);

        assertEquals(name, badProduct.getName());
        assertEquals(quantity, badProduct.getQuantity());
    }

    @Test
    void testAllArgsConstructor() {
        String name = "Product B";
        Integer quantity = 5;

        BadProduct badProduct = new BadProduct(name, quantity);

        assertEquals(name, badProduct.getName());
        assertEquals(quantity, badProduct.getQuantity());
    }

    @Test
    void testNoArgsConstructor() {
        BadProduct badProduct = new BadProduct();
        assertNotNull(badProduct);
    }
}
