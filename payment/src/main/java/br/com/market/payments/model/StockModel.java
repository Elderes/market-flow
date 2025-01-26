package br.com.market.payments.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter @Setter
@Entity
@Table(name = "tb_stock")
public class StockModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private UUID order_id;
    private UUID client_id;
    private boolean approval;
    private BigDecimal totalValue;
}
