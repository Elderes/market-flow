package com.accenture_projeto.seller.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import com.accenture_projeto.seller.dto.ProductModelDTO;

@Entity
@Table(name = "tb_product")
@Data
@NoArgsConstructor
public class ProductModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public ProductModel(ProductModelDTO dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.quantity = dto.getQuantity();
        this.price = dto.getPrice();
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private SellerModel seller;
}
