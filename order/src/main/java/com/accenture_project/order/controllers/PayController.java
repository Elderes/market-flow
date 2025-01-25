package com.accenture_project.order.controllers;

import com.accenture_project.order.dtos.PayDTO;
import com.accenture_project.order.exceptions.InvalidPayException;
import com.accenture_project.order.mappers.PayMapper;
import com.accenture_project.order.services.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> pay(@RequestBody PayDTO payDTO) {
        try {
            var payModel = payMapper.toPayModel(payDTO);

            payService.validatePayment(payModel);
            payService.payOrder(payModel);

            return ResponseEntity.status(HttpStatus.OK).body("Pagamento realizado com sucesso!");
        } catch (InvalidPayException | NegativeArraySizeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro inesperado: " + e.getMessage());
        }




    }
}
