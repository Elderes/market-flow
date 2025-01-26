package com.accenture_project.send.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Entity class representing an order.
 * It maps to the "tb_order" table in the database and stores order details.
 */

@Getter @Setter
@Entity
@Table(name = "tb_order")
public class OrderModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private ClientModel client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductModel> products = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime orderDateTime;
}
