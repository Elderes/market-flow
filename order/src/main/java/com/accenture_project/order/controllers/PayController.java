package com.accenture_project.order.controllers;

import com.accenture_project.order.dtos.PayDTO;
import com.accenture_project.order.mappers.PayMapper;
import com.accenture_project.order.services.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PayController {

    private final PayService payService;
    private final PayMapper payMapper;

    @PostMapping("/pay")
    public ResponseEntity<?> pay(@RequestBody PayDTO payDTO) {
        payService.payOrder(payMapper.toPayModel(payDTO));
        return ResponseEntity.ok().build();
    }
}
