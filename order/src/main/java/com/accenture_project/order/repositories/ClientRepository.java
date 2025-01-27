package com.accenture_project.order.repositories;

import com.accenture_project.order.models.ClientModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends CrudRepository<ClientModel, UUID> {
}
