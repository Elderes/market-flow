package com.accenture_project.send.services;

import com.accenture_project.send.exceptions.NoProductException;
import com.accenture_project.send.models.ProductModel;
import com.accenture_project.send.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service class responsible for handling operations related to products.
 *
 * - verifyProduct: Verifies if the given product exists in the repository by its ID.
 * - getAllProducts: Retrieves all products from the repository. Throws NoProductException if no products are found.
 * - getProduct: Retrieves a specific product by its ID. Throws NoProductException if the product is not found.
 */

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductModel verifyProduct(ProductModel product) {
        return productRepository.findById(product.getId()).orElse(product);
    }

    public List<ProductModel> getAllProducts() {
        var products = productRepository.findAll();

        if (products.isEmpty()) {
            throw new NoProductException("There are no products");
        }

        return products;
    }

    public ProductModel getProduct(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoProductException("Product not found with id:" + id));
    }
}
