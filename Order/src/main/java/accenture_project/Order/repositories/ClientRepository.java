package accenture_project.Order.repositories;

import accenture_project.Order.models.ClientModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends CrudRepository<ClientModel, UUID> {
}
