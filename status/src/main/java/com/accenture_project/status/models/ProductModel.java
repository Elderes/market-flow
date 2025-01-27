package com.accenture_project.status.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Entity class representing a product.
 * It maps to the "tb_product" table in the database and stores product details.
 */

@Getter @Setter
@Entity
@Table(name = "tb_product")
public class ProductModel implements Serializable {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer quantity;

    private BigDecimal unitPrice;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;
}