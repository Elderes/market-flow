package br.summer_academy.stock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
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
