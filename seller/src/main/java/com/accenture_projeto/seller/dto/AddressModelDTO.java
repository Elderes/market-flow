package com.accenture_projeto.seller.dto;

import lombok.Data;

@Data
public class AddressModelDTO {
    private String country;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private Integer number;
}
