package com.accenture_project.send.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * Entity class representing an address.
 * It maps to the "tb_address" table in the database and stores the address details of a client.
 */

@Getter @Setter
@Entity
@Table(name = "tb_address")
public class AddressModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private Integer number;
}