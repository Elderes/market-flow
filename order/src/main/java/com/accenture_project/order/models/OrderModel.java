package com.accenture_project.order.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/*
 * ClientModel Class
 *
 * This class represents a client in the system, mapped to the "tb_client" table.
 * It includes fields for the client's name, cellphone, email, and an associated address.
 * The address is mapped to the AddressModel entity with a one-to-one relationship.
 * The class is annotated as a JPA entity, with a UUID as the primary key.
 */

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
