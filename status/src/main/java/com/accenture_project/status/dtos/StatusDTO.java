package com.accenture_project.status.dtos;

import java.util.UUID;

/**
 * DTO representing the status of an order with the email of the client and order ID.
 */

public record StatusDTO(String email,
                        UUID orderId) {
}
