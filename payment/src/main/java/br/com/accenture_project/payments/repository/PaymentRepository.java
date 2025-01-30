package br.com.accenture_project.payments.repository;

import br.com.accenture_project.payments.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel, UUID> {
    Optional<PaymentModel> findByOrderId(UUID orderId);
}