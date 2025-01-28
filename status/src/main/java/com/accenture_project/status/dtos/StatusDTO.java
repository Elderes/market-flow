package com.accenture_project.status.dtos;

import java.time.LocalDateTime;

public record StatusDTO(boolean wasPaid,
                        LocalDateTime lastUpdate) {
}
