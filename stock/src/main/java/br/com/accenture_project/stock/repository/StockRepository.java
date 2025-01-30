package br.com.accenture_project.stock.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.accenture_project.stock.model.Product;

public interface StockRepository extends JpaRepository<Product, UUID> {
    Product findByName(String name);
}
