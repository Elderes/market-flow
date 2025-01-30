package br.com.accenture_project.status.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class StatusModelTest {

    @Test
    public void testStatusModel() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        LocalDateTime lastUpdate = LocalDateTime.now();
        String emailClient = "lucazmatehus14@gmail.com";
        BigDecimal totalPrice = new BigDecimal("199.99");

        StatusModel statusModel = new StatusModel();
        statusModel.setOrderId(orderId);
        statusModel.setWasPaid(true);
        statusModel.setLastUpdate(lastUpdate);
        statusModel.setEmailClient(emailClient);
        statusModel.setTotalPrice(totalPrice);

        // Act & Assert
        assertNotNull(statusModel);
        assertEquals(orderId, statusModel.getOrderId());
        assertTrue(statusModel.isWasPaid());
        assertEquals(lastUpdate, statusModel.getLastUpdate());
        assertEquals(emailClient, statusModel.getEmailClient());
        assertEquals(totalPrice, statusModel.getTotalPrice());
    }
}
