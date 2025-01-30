package br.com.accenture_project.order.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class OrderModelTest {

    private OrderModel order;
    private ClientModel client;

    @BeforeEach
    public void setUp() {
        client = new ClientModel();
        client.setId(UUID.randomUUID());
        client.setName("Lucas");

        order = new OrderModel();
        order.setId(UUID.randomUUID());
        order.setClient(client);
        order.setOrderDateTime(LocalDateTime.now());
    }

    @Test
    public void testOrderModel_GettersAndSetters() {
        UUID id = UUID.randomUUID();
        LocalDateTime orderDateTime = LocalDateTime.now();

        order.setId(id);
        order.setOrderDateTime(orderDateTime);

        assertEquals(id, order.getId());
        assertEquals(orderDateTime, order.getOrderDateTime());
    }

    @Test
    public void testOrderModel_ClientRelationship() {
        assertNotNull(order.getClient());
        assertEquals(client, order.getClient());
    }

    @Test
    public void testOrderModel_NoArgsConstructor() {
        OrderModel emptyOrder = new OrderModel();
        assertNull(emptyOrder.getId());
        assertNull(emptyOrder.getClient());
        assertNull(emptyOrder.getOrderDateTime());
    }

    @Test
    public void testOrderModel_AllArgsConstructor() {
        UUID id = UUID.randomUUID();
        LocalDateTime orderDateTime = LocalDateTime.now();

        OrderModel newOrder = new OrderModel(id, client, orderDateTime);

        assertEquals(id, newOrder.getId());
        assertEquals(client, newOrder.getClient());
        assertEquals(orderDateTime, newOrder.getOrderDateTime());
    }
}