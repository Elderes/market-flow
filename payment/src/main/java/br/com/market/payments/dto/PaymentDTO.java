package br.com.market.payments.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


public record PaymentDTO(UUID id, BigDecimal totalPrice, LocalDateTime dateTimeOfPayment, UUID orderId){

}