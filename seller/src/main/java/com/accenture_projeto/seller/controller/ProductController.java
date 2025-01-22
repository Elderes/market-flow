package com.accenture_projeto.seller.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture_projeto.seller.service.ProductSender;

@RestController
@RequestMapping("/team_3/seller")
public class ProductController {
    private final ProductSender productSender;

    public ProductController(ProductSender productSender) {
        this.productSender = productSender;
    }
    
    @GetMapping("/sendProductList")
    public String sendMessage(@RequestParam String message) {
        productSender.sendProductList("my-exchange", "my-routing-key", message);
        return "Message sent: " + message;
    }
}
