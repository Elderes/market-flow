package com.accenture_project.status.controllers;

import com.accenture_project.status.exceptions.NoProductException;
import com.accenture_project.status.models.ProductModel;
import com.accenture_project.status.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * This controller provides endpoints for retrieving products.
 *
 * - GET /products: Fetches a list of all products.
 * - GET /product/{id}: Fetches a specific product by its ID.
 *
 * Exceptions:
 * - Handles NoProductException by logging and returning a 500 Internal Server Error.
 * - Handles general exceptions by logging and returning a 500 Internal Server Error.
 */

@RequiredArgsConstructor
@RestController
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        try {
            var products = productService.getAllProducts();

            return ResponseEntity.ok().body(products);
        } catch (NoProductException e) {
            logger.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving products", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable("id") UUID id) {
        try {
            var product = productService.getProduct(id);

            return ResponseEntity.ok().body(product);
        } catch (NoProductException e) {
            logger.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving product", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
