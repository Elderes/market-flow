package com.accenture_project.order.controllers;

import com.accenture_project.order.dtos.OrderDTO;
import com.accenture_project.order.exceptions.InvalidAddressException;
import com.accenture_project.order.exceptions.InvalidClientException;
import com.accenture_project.order.exceptions.InvalidProductException;
import com.accenture_project.order.exceptions.NoOrderException;
import com.accenture_project.order.mappers.OrderMapper;
import com.accenture_project.order.models.OrderModel;
import com.accenture_project.order.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping("/order")
    public ResponseEntity<String> saveOrder(@RequestBody OrderDTO orderDTO) {
        try {
            var order = orderMapper.toOrderModel(orderDTO);

            orderService.validateOrder(order, orderDTO.products());
            orderService.saveOrder(order);
            orderService.publishOrder(order, orderDTO.products());
//            orderService.sendEmail(order);

            return ResponseEntity.status(HttpStatus.CREATED).body("Order created successfully!");
        } catch (InvalidClientException | InvalidAddressException | InvalidProductException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderModel>> getOrders() {
        try {
            var orders = orderService.getOrders();

            return ResponseEntity.ok().body(orders);
        } catch (NoOrderException e) {
            logger.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving orders ", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderModel> getOrderById(@PathVariable("id") UUID id) {
        try {
            var order = orderService.getOrder(id);

            return ResponseEntity.ok().body(order);
        } catch (NoOrderException e) {
            logger.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            logger.error("Error retrieving order ", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") UUID id) {
        try {
            orderService.deleteById(id);

            return ResponseEntity.ok().body("Order successfully deleted");
        } catch (NoOrderException e) {
            logger.error("Error retrieving order", e);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error deleting order");
        } catch (Exception e) {
            logger.error("Error deleting order ", e);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Error deleting order");
        }
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable("id") UUID id, @RequestBody OrderDTO orderDTO) {
        try {
            var order = orderMapper.toOrderModel(orderDTO);

            orderService.validateOrder(order, orderDTO.products());
            orderService.updateOrder(id, orderDTO);

            logger.info("Order successfully updated");

            return ResponseEntity.status(HttpStatus.OK).body("Order updated successfully!");
        } catch (InvalidClientException | InvalidAddressException | InvalidProductException e) {
            logger.error("Error updating order", e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error updating order ", e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unexpected error: " + e.getMessage());
        }
    }
}
