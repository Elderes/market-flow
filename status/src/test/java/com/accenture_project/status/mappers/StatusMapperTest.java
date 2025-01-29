package com.accenture_project.status.mappers;

import static org.junit.jupiter.api.Assertions.*;

import com.accenture_project.status.dtos.StatusDTO;
import com.accenture_project.status.models.StatusModel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class StatusMapperTest {

    @Test
    public void testToStatusModel() {
        // Arrange
        StatusModel statusModel = new StatusModel();
        statusModel.setEmailClient("lucazmatehus14@gmail.com");
        var id = UUID.randomUUID();
        statusModel.setOrderId(id);

        StatusMapper statusMapper = new StatusMapper();

        // Act
        StatusDTO result = statusMapper.toStatusModel(statusModel);

        // Assert
        assertNotNull(result);
        assertEquals("lucazmatehus14@gmail.com", result.email());
        assertEquals(id, result.orderId());
    }
}