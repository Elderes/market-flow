package com.accenture_projeto.seller.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

import com.accenture_projeto.seller.dto.AddressModelDTO;

@Entity
@Table(name = "tb_address")
@Data
public class AddressModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public AddressModel(AddressModelDTO dto) {
        this.country = dto.getCountry();
        this.state = dto.getState();
        this.city = dto.getCity();
        this.neighborhood = dto.getNeighborhood();
        this.street = dto.getStreet();
        this.number = dto.getNumber();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
