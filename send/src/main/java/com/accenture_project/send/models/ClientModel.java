package com.accenture_project.send.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * Entity class representing a client.
 * It maps to the "tb_client" table in the database and stores the details of the client.
 */

@Getter @Setter
@Entity
@Table(name = "tb_client")
public class ClientModel implements Serializable {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cellphone;

    @Column(nullable = false)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private  AddressModel address;
}
