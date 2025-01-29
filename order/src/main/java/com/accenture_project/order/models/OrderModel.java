package com.accenture_project.order.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_order")
public class OrderModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private ClientModel client;

    @Column(nullable = false)
    private LocalDateTime orderDateTime;
}
