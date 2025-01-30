package br.com.accenture_project.order.repositories;

import br.com.accenture_project.order.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, UUID> {
}
