package com.accenture_project.send.models;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SendModelTest {

    @Test
    void testSendModel() {
        UUID id = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        String email = "lucazmatehus14@gmail.com";

        SendModel sendModel = new SendModel();
        sendModel.setId(id);
        sendModel.setHasSend(true);
        sendModel.setOrderId(orderId);
        sendModel.setEmail(email);

        assertEquals(id, sendModel.getId());
        assertTrue(sendModel.isHasSend());
        assertEquals(orderId, sendModel.getOrderId());
        assertEquals(email, sendModel.getEmail());
    }

    @Test
    void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        String email = "lucazmatehus14@gmail.com";

        SendModel sendModel = new SendModel(id, true, orderId, email);

        assertEquals(id, sendModel.getId());
        assertTrue(sendModel.isHasSend());
        assertEquals(orderId, sendModel.getOrderId());
        assertEquals(email, sendModel.getEmail());
    }
}
