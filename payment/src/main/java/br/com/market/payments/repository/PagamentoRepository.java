package br.com.market.payments.repository;

import br.com.market.payments.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>  {

}
