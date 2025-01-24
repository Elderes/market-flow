package com.accenture_projeto.seller.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.accenture_projeto.seller.repository.ProductRepository;

@Service
public class SellerProducer {
    private final RabbitTemplate rabbitTemplate;
    private ProductRepository productRepository;

    @Value("${products.list.queue}")
    private String productsListQueue;

    @Value("${routing.key.list}")
    private String routingKeyList;

    @Autowired
    SellerProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    public void sendProductList() {
        rabbitTemplate.convertAndSend(productsListQueue, routingKeyList, productRepository.findAll());
    }
}
