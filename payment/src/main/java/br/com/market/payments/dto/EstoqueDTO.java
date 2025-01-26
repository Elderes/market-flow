package br.com.market.payments.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class EstoqueDTO {  //Vou receber de Abraão
    private int order_Id;
    private int client_ID;
    private boolean approval;
    private BigDecimal totalValue;
}
