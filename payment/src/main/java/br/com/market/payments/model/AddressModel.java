package br.com.market.payments.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter @Setter
@Entity
@Table(name = "tb_address")
public class AddressModel implements Serializable {
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