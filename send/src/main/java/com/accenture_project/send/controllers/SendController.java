package com.accenture_project.send.controllers;

import com.accenture_project.send.models.OrderModel;
import com.accenture_project.send.services.SendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SendController {

    private final SendService sendService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderModel>> getOrders() {
        return ResponseEntity.ok().body(sendService.getOrders());
    }
}
