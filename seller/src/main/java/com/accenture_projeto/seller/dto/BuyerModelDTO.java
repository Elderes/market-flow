package com.accenture_projeto.seller.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class BuyerModelDTO {
    private UUID id;
    private String name;
    private String email;
    private AddressModelDTO address;
    private String cellphone;
}

