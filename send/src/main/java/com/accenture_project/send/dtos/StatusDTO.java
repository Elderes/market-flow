package com.accenture_project.send.dtos;

import java.util.UUID;

public record StatusDTO(String email,
                        UUID orderId) {
}
