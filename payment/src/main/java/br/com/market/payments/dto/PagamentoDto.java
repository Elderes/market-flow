package br.com.market.payments.dto;

import br.com.market.payments.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDto {
    private Long id;
    private String code;
    private BigDecimal value;
    private LocalDateTime dateTime;


}
