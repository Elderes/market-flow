package com.accenture_project.send.repositories;

import com.accenture_project.send.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing Client entities.
 * Extends CrudRepository to provide CRUD operations for ClientModel.
 */

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, UUID> {
}
