package br.summer_academy.stock.model;

import java.util.UUID;

import lombok.Data;

@Data
public class Address {
    private UUID id;
    private String country;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String number;
}
