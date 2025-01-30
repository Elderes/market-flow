package br.summer_academy.stock.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    void testOrderModel() {
        UUID id = UUID.randomUUID();
        Client client = new Client(UUID.randomUUID(), "John Doe", "123456789", "john@example.com",
                new Address(UUID.randomUUID(), "USA", "California", "Los Angeles", "Downtown", "Main St", "123"));

        List<Product> products = new ArrayList<>();
        products.add(new Product(UUID.randomUUID(), "Laptop", 2, BigDecimal.ONE));
        products.add(new Product(UUID.randomUUID(), "Mouse", 5, BigDecimal.ONE));

        LocalDateTime orderDateTime = LocalDateTime.now();

        Order order = new Order();
        order.setId(id);
        order.setClient(client);
        order.setProducts(products);
        order.setOrderDateTime(orderDateTime);

        assertEquals(id, order.getId());
        assertEquals(client, order.getClient());
        assertEquals(products, order.getProducts());
        assertEquals(orderDateTime, order.getOrderDateTime());
    }

    @Test
    void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        Client client = new Client(UUID.randomUUID(), "Jane Doe", "987654321", "jane@example.com",
                new Address(UUID.randomUUID(), "Canada", "Ontario", "Toronto", "Midtown", "Queen St", "456"));

        List<Product> products = new ArrayList<>();
        products.add(new Product(UUID.randomUUID(), "Monitor", 1, BigDecimal.ONE));
        products.add(new Product(UUID.randomUUID(), "Keyboard", 3, BigDecimal.ONE));

        LocalDateTime orderDateTime = LocalDateTime.now();

        Order order = new Order(id, client, products, orderDateTime);

        assertEquals(id, order.getId());
        assertEquals(client, order.getClient());
        assertEquals(products, order.getProducts());
        assertEquals(orderDateTime, order.getOrderDateTime());
    }

    @Test
    void testNoArgsConstructor() {
        Order order = new Order();
        assertNotNull(order);
        assertNotNull(order.getProducts()); // Deve ser inicializado como uma lista vazia
        assertTrue(order.getProducts().isEmpty());
    }
}
