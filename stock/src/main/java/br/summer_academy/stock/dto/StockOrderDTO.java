package br.summer_academy.stock.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.summer_academy.stock.model.Product;
import lombok.Data;

@Data
public class StockOrderDTO {
    private UUID orderId;
    private List<Product> products;
    private LocalDateTime orderDateTime;
    private boolean approval;
}
