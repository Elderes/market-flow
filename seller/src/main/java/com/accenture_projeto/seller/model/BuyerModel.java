package com.accenture_projeto.seller.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

import com.accenture_projeto.seller.dto.BuyerModelDTO;

@Entity
@Table(name = "tb_buyer")
@Data
@NoArgsConstructor
public class BuyerModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public BuyerModel(BuyerModelDTO dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.address = new AddressModel(dto.getAddress());
        this.cellphone = dto.getCellphone();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressModel address;

    @Column(nullable = false)
    private String cellphone;
}
