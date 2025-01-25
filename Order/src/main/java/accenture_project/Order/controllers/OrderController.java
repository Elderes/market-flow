package accenture_project.Order.controllers;

import accenture_project.Order.dtos.OrderDTO;
import accenture_project.Order.mappers.OrderMapper;
import accenture_project.Order.models.OrderModel;
import accenture_project.Order.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<OrderModel> salveOrder(@RequestBody @Valid OrderDTO orderDTO) {
        var order = orderMapper.toOrderModel(orderDTO);

        orderService.saveOrder(order);
        orderService.publishOrder(order);

        return ResponseEntity.ok().body(order);
    }
}
