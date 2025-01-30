package br.com.accenture_project.stock.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {
    private UUID id;
    private String name;
    private String cellphone;
    private String email;
    private Address address;
}
