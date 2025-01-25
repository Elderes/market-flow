package br.summer_academy.stock.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class Order {
    private UUID id;

    private Client client;

    private List<Product> products = new ArrayList<>();

    private LocalDateTime orderDateTime;
}