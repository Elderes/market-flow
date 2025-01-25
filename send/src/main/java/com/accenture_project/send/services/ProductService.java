package com.accenture_project.send.services;

import com.accenture_project.send.models.ProductModel;
import com.accenture_project.send.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductModel verifyProduct(ProductModel product) {
        return productRepository.findById(product.getId()).orElse(product);
    }
}
