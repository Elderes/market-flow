package com.accenture_project.order.controllers;

import com.accenture_project.order.dtos.OrderDTO;
import com.accenture_project.order.exceptions.InvalidAddressException;
import com.accenture_project.order.exceptions.InvalidClientException;
import com.accenture_project.order.exceptions.InvalidProductException;
import com.accenture_project.order.mappers.OrderMapper;
import com.accenture_project.order.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping("/order")
    public ResponseEntity<String> saveOrder(@RequestBody OrderDTO orderDTO) {
        try {
            var order = orderMapper.toOrderModel(orderDTO);

            orderService.validateOrder(order);
            orderService.saveOrder(order);
            orderService.publishOrder(order);
            orderService.sendEmail(order);

            return ResponseEntity.status(HttpStatus.CREATED).body("Pedido criado com sucesso!");
        } catch (InvalidClientException | InvalidAddressException | InvalidProductException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro inesperado: " + e.getMessage());
        }
    }
}
