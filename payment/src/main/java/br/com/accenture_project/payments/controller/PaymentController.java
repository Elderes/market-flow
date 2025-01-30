package br.com.accenture_project.payments.controller;

import br.com.accenture_project.payments.dto.PayDTO;
import br.com.accenture_project.payments.exception.InvalidValueException;
import br.com.accenture_project.payments.exception.PaymentNotFoundException;
import br.com.accenture_project.payments.model.PaymentModel;
import br.com.accenture_project.payments.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/*
 * Manages payment-related operations via RESTful endpoints.
 * - Handles the payment process, including creating and deleting payments, and retrieving payment information.
 * - Provides endpoints to:
 *   - `POST /pay`: Process payment for an order.
 *   - `GET /payments`: Retrieve a list of all payments.
 *   - `GET /payment/{id}`: Retrieve payment details by ID.
 *   - `DELETE /payment/{id}`: Delete a payment by its ID.
 * - Catches specific exceptions like `PaymentNotFoundException` and `InvalidValueException`, and provides appropriate error responses.
 * - Logs payment operations and errors.
 */


@RequiredArgsConstructor
@RestController
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity<String> payOrder(@RequestBody PayDTO payDTO) {
        try {
            paymentService.pay(payDTO);

            logger.info("Payment successful");

            return ResponseEntity.ok().body("Order paid successfully!");
        } catch (PaymentNotFoundException e) {
            logger.error("Payment not found", e);

            return ResponseEntity.badRequest().body("Wrong order code!");
        } catch (InvalidValueException e) {
            logger.error("Payment amount less than total order amount!");

            return ResponseEntity.badRequest().body("Payment amount less than total order amount!");
        } catch (Exception e) {
            logger.error("Payment error", e);

            return ResponseEntity.badRequest().body("Payment error!");
        }
    }

    @GetMapping("/payments")
    public ResponseEntity<List<PaymentModel>> getPayments() {
        try {
            var payments = paymentService.findAllPayments();

            logger.info("Payments found");

            return ResponseEntity.ok().body(payments);
        } catch (Exception e) {
            logger.error("Payment error", e);

            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<PaymentModel> getPaymentById(@PathVariable UUID id) {
        try {
            var payment = paymentService.findPaymentById(id);

            logger.info("Payment found");

            return ResponseEntity.ok().body(payment);
        } catch (PaymentNotFoundException e) {
            logger.error("Payment not found", e);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.error("Payment error", e);

            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/payment/{id}")
    public ResponseEntity<String> deletePaymentById(@PathVariable UUID id) {
        try {
            paymentService.deletePayment(id);

            logger.info("Payment successful");

            return ResponseEntity.ok().body("Payment deleted successfully!");
        } catch (PaymentNotFoundException e) {
            logger.error("Payment not found", e);

            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.error("Payment error", e);

            return ResponseEntity.badRequest().build();
        }
    }
}
