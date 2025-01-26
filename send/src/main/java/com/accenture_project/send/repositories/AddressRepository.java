package com.accenture_project.send.repositories;

import com.accenture_project.send.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing Address entities.
 * Extends JpaRepository to provide CRUD operations for AddressModel.
 */

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, UUID> {
}
