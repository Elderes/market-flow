package com.accenture_project.order.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_client")
public class ClientModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cellphone;

    @Column(nullable = false)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private AddressModel address;
}
