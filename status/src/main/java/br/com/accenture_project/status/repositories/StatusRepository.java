package br.com.accenture_project.status.repositories;

import br.com.accenture_project.status.models.StatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing Address entities.
 * Extends JpaRepository to provide CRUD operations for AddressModel.
 */

@Repository
public interface StatusRepository extends JpaRepository<StatusModel, UUID> {
    Optional<StatusModel> findByOrderId(UUID orderId);
}
