package com.accenture_projeto.seller.service;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.accenture_projeto.seller.model.ProductModel;
import com.accenture_projeto.seller.repository.ProductRepository;

@Service
public class SellerProducer {
    private final RabbitTemplate rabbitTemplate;

    private final ProductRepository productRepository;

    
    @Value("${products.list.queue}")
    private String productsListQueue;
    
    @Value("${routing.key.products.list}")
    private String routingKeyList;

    @Value("${exchange-direct}")
    private String exchange;
    
    @Autowired
    SellerProducer(RabbitTemplate rabbitTemplate, ProductRepository productRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.productRepository = productRepository;
    }
    
    public void sendProductList() {
        List<ProductModel> productList = productRepository.findAll();
        rabbitTemplate.convertAndSend(exchange, routingKeyList, productList);
    }
}
