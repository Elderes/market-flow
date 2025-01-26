package br.com.market.payments.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class EstoqueDTO {  //Vou receber de Abraão
    private int produtoId;
    private int clienteID;
    private boolean produtosDisponiveis;
    private BigDecimal valorTotal;
}
