package com.accenture_projeto.buyer.services;

import com.accenture_projeto.buyer.models.BuyerModel;
import com.accenture_projeto.buyer.producers.ProductsListProducer;
import com.accenture_projeto.buyer.repositories.BuyerRepository;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {

    private final BuyerRepository buyerRepository;
    private final ProductsListProducer productsListProducer;

    public BuyerService(BuyerRepository buyerRepository, ProductsListProducer productsListProducer) {
        this.buyerRepository = buyerRepository;
        this.productsListProducer = productsListProducer;
    }

    public BuyerModel save(BuyerModel buyerModel) {
        return buyerRepository.save(buyerModel);
    }

    public void requestProductList(String message) {
        productsListProducer.publishMessage(message);
    }
}
