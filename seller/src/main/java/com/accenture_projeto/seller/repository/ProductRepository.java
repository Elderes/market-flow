package com.accenture_projeto.seller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture_projeto.seller.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
