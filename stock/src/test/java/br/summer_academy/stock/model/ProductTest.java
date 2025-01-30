package br.summer_academy.stock.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void testProductModel() {
        UUID id = UUID.randomUUID();
        String name = "Laptop";
        int quantity = 10;
        BigDecimal price = new BigDecimal("1500.00");

        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setQuantity(quantity);
        product.setPrice(price);

        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(quantity, product.getQuantity());
        assertEquals(price, product.getPrice());
    }

    @Test
    void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        String name = "Smartphone";
        int quantity = 5;
        BigDecimal price = new BigDecimal("800.00");

        Product product = new Product(id, name, quantity, price);

        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(quantity, product.getQuantity());
        assertEquals(price, product.getPrice());
    }

    @Test
    void testNoArgsConstructor() {
        Product product = new Product();
        assertNotNull(product);
    }
}
