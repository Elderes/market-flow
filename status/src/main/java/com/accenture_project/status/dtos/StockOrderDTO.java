package com.accenture_project.status.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class StockOrderDTO {
    private UUID order_id;
    private UUID client_id;
    private boolean approval;
}
