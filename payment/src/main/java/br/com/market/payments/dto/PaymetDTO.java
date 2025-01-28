package br.com.market.payments.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


public record PaymetDTO (UUID id, BigDecimal totalPrice,LocalDateTime dateTimeOfPayment, UUID orderId){

}