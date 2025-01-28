package br.com.market.payments.repository;

import br.com.market.payments.model.PaymentModel;
import br.com.market.payments.model.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel, UUID> {
}
