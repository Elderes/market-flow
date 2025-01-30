package br.com.accenture_project.stock.dto;

import java.util.List;
import java.util.UUID;

import br.com.accenture_project.stock.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockOrderDTO {
    private UUID orderId;
    private List<Product> products;
    private boolean approval;
}
