package com.accenture_project.send.repositories;

import com.accenture_project.send.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing Product entities.
 * Extends JpaRepository to provide CRUD operations for ProductModel.
 */

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
}
