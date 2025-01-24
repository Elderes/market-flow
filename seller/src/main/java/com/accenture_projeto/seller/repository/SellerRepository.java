package com.accenture_projeto.seller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture_projeto.seller.model.SellerModel;

public interface SellerRepository extends JpaRepository<SellerModel, Integer> {

}
