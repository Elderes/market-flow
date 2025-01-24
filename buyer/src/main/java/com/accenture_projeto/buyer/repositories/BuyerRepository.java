package com.accenture_projeto.buyer.repositories;

import com.accenture_projeto.buyer.models.BuyerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerModel, UUID> {
    Optional<BuyerModel> findByCellphone(String cellphone);
}
