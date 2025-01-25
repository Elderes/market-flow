package br.summer_academy.stock.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.summer_academy.stock.model.Product;

public interface StockRepository extends JpaRepository<Product, UUID> {
    Product findByName(String name);
}
