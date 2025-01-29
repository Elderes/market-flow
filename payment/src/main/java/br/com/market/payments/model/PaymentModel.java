package br.com.market.payments.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/*
 * Represents a payment model for an order.
 * - Contains fields for payment ID, total price, payment date and time, associated order ID, arrival time, payment status, stock confirmation, and client's email.
 * - Used to track and store payment information for orders in the system.
 */


@Getter
@Setter
@Entity
@Table(name = "tb_payment")
public class PaymentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private BigDecimal totalPrice;
    private LocalDateTime dateTimeOfPayment;
    private UUID orderId;
    private LocalDateTime orderArrivalTime;
    private boolean hasPaid;
    private boolean stockConfirmed;
    private String emailClient;
}
