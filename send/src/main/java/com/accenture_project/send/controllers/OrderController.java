package com.accenture_project.send.controllers;

import com.accenture_project.send.exceptions.NoOrderException;
import com.accenture_project.send.models.OrderModel;
import com.accenture_project.send.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * This controller provides endpoints for retrieving and managing orders.
 *
 * - GET /orders: Fetches a list of all orders.
 * - GET /order/{id}: Fetches a specific order by its ID.
 * - DELETE /order: Deletes a specific order by its ID.
 *
 * Exceptions:
 * - Handles NoOrderException by logging and returning a 500 Internal Server Error.
 * - Handles general exceptions by logging and returning a 500 Internal Server Error for GET requests.
 * - Handles general exceptions by logging and returning a 500 Internal Server Error for DELETE requests.
 */

@RequiredArgsConstructor
@RestController
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderModel>> getOrders() {
        try {
            var orders = orderService.getOrders();

            return ResponseEntity.ok().body(orders);
        } catch (NoOrderException e) {
            logger.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving orders", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/order")
    public ResponseEntity<OrderModel> getOrderById(@RequestBody UUID id) {
        try {
            var order = orderService.getOrder(id);

            return ResponseEntity.ok().body(order);
        } catch (NoOrderException e) {
            logger.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            logger.error("Error retrieving order", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/order")
    public ResponseEntity<String> deleteOrder(@RequestBody UUID id) {
        try {
            orderService.deleteById(id);

            return ResponseEntity.ok().body("Order successfully deleted");
        } catch (NoOrderException e) {
            logger.error("Error retrieving order", e);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error deleting order");
        } catch (Exception e) {
            logger.error("Error deleting order", e);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Error deleting order");
        }
    }
}
