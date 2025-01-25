package accenture_project.Order.controllers;

import accenture_project.Order.dtos.PayDTO;
import accenture_project.Order.mappers.PayMapper;
import accenture_project.Order.services.PayService;
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
