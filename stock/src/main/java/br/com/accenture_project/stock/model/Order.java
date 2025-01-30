package br.com.accenture_project.stock.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter 
@Setter
public class Order {
    private UUID id;

    private Client client;

    private List<Product> products = new ArrayList<>();

    private LocalDateTime orderDateTime;
}