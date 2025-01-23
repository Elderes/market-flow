package com.accenture_projeto.buyer.services;

import com.accenture_projeto.buyer.models.BuyerModel;
import com.accenture_projeto.buyer.models.ProductModel;
import com.accenture_projeto.buyer.producers.RequestProductsListProducer;
import com.accenture_projeto.buyer.producers.SendOrderProducer;
import com.accenture_projeto.buyer.repositories.BuyerRepository;
import com.accenture_projeto.buyer.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class BuyerService {

    private final BuyerRepository buyerRepository;
    private final RequestProductsListProducer productsListProducer;
    private final SendOrderProducer sendOrderProducer;
    private final ProductRepository productRepository;

    public BuyerService(BuyerRepository buyerRepository, RequestProductsListProducer productsListProducer, SendOrderProducer sendOrderProducer, ProductRepository productRepository) {
        this.buyerRepository = buyerRepository;
        this.productsListProducer = productsListProducer;
        this.sendOrderProducer = sendOrderProducer;
        this.productRepository = productRepository;
    }

    public BuyerModel save(BuyerModel buyerModel) {
        return buyerRepository.save(buyerModel);
    }

    public void requestProductList(String message) {
        productsListProducer.publishMessage(message);
    }

    public void sendProducts() {
        var product1 = new ProductModel();
        product1.setName("Product1");
        product1.setDescription("Product1 Description");
        product1.setQuantity(2.);
        product1.setPrice(new BigDecimal(5));
        productRepository.save(product1);

        var product2 = new ProductModel();
        product2.setName("Product2");
        product2.setDescription("Product2 Description");
        product2.setQuantity(3.);
        product2.setPrice(new BigDecimal(88));
        productRepository.save(product2);



        List<ProductModel> products = productRepository.findAll();
        sendOrderProducer.publishMessage(products);
    }
}
