package com.accenture_project.send.repositories;

import com.accenture_project.send.models.SendModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SendRepository extends JpaRepository<SendModel, UUID> {
}
