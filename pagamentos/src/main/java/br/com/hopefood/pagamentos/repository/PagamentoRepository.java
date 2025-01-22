package br.com.hopefood.pagamentos.repository;

import br.com.hopefood.pagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>  {

}
