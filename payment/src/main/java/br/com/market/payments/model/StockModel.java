package br.com.market.payments.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@Table(name = "tb_stock")
public class StockModel {
    private UUID orderId;

    private List<ProductModel> products;

    private LocalDateTime orderDateTime;
}
