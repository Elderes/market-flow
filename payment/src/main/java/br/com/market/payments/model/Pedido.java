package br.com.market.payments.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pedidoId;
    private String name;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Product> products;

    // Classe Client
    @Getter
    @Setter
    @Entity
    public static class Client {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String cellphone;
        private String email;

        @Embedded
        private Address address;

        // Classe Address
        @Getter
        @Setter
        @Embeddable
        public static class Address {
            private String country;
            private String state;
            private String city;
            private String neighborhood;
            private String street;
            private int number;
        }
    }

    // Classe Product
    @Getter
    @Setter
    @Entity
    public static class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private int quantity;

        @ManyToOne
        @JoinColumn(name = "pedido_id")
        private Pedido pedido;
    }
}
