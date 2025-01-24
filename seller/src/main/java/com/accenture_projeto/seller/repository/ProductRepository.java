package com.accenture_projeto.seller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture_projeto.seller.model.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Integer> {

}
