package com.accenture_project.status.dtos;

import java.util.UUID;

public record StatusDTO(String email,
                        UUID orderId) {
}
