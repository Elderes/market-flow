package br.summer_academy.stock.model;

import java.util.UUID;

import lombok.Data;

@Data
public class Client {
    private UUID id;
    private String name;
    private String cellphone;
    private String email;
    private Address address;
}
