package br.com.market.payments.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_pay")
@Getter @Setter
public class PayModel implements Serializable {
    @Id
    private UUID id;
    private String code;
    private BigDecimal value;
    private LocalDateTime dateTime;
}
