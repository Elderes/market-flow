package com.accenture_projeto.buyer.services;

import com.accenture_projeto.buyer.exceptions.BuyerNotFoundException;
import com.accenture_projeto.buyer.exceptions.ProductNotFoundException;
import com.accenture_projeto.buyer.models.BuyerModel;
import com.accenture_projeto.buyer.models.OrderModel;
import com.accenture_projeto.buyer.models.ProductModel;
import com.accenture_projeto.buyer.producers.RequestProductsListProducer;
import com.accenture_projeto.buyer.producers.SendOrderProducer;
import com.accenture_projeto.buyer.repositories.BuyerRepository;
import com.accenture_projeto.buyer.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerService {

    private final BuyerRepository buyerRepository;
    private final RequestProductsListProducer productsListProducer;
    private final ProductRepository productRepository;
    private final SendOrderProducer sendOrderProducer;

    public BuyerService(BuyerRepository buyerRepository, RequestProductsListProducer productsListProducer, ProductRepository productRepository, SendOrderProducer sendOrderProducer) {
        this.buyerRepository = buyerRepository;
        this.productsListProducer = productsListProducer;
        this.productRepository = productRepository;
        this.sendOrderProducer = sendOrderProducer;
    }

    public BuyerModel saveBuyer(BuyerModel buyerModel) {
        return buyerRepository.save(buyerModel);
    }

    public BuyerModel getBuyerByCellphone(String cellphone) {
        return buyerRepository.findByCellphone(cellphone).orElseThrow(() -> new BuyerNotFoundException("Buyer not found with cellphone: " + cellphone));
    }

    public void saveProduct(ProductModel productModel) {
        productRepository.save(productModel);
    }

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductModel getProductByName(String productName) {
        return productRepository.findByName(productName).orElseThrow(() -> new ProductNotFoundException("Product not found with name: " + productName));
    }

    public void requestProductList(String message) {
        productsListProducer.publishMessage(message);
    }

    public void sendProducts(BuyerModel buyerModel, List<ProductModel> productModels) {
        var order = new OrderModel(buyerModel, productModels);
        sendOrderProducer.publishMessage(order);
    }
}
