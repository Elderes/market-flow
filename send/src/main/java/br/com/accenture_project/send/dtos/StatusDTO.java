package br.com.accenture_project.send.dtos;

import java.util.UUID;

/*
 * StatusDTO Class
 *
 * Represents the data transfer object (DTO) for the status message.
 * Contains the email and orderId to indicate the status of the order.
 */

public record StatusDTO(String email,
                        UUID orderId) {
}
