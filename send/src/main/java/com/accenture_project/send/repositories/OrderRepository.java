package com.accenture_project.send.repositories;

import com.accenture_project.send.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing Order entities.
 * Extends JpaRepository to provide CRUD operations for OrderModel.
 */

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, UUID> {
}
