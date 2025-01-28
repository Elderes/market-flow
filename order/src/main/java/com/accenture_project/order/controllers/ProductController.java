package com.accenture_project.order.controllers;

import com.accenture_project.order.dtos.AddressDTO;
import com.accenture_project.order.dtos.ProductDTO;
import com.accenture_project.order.exceptions.InvalidAddressException;
import com.accenture_project.order.exceptions.InvalidProductException;
import com.accenture_project.order.exceptions.NoAddressException;
import com.accenture_project.order.exceptions.NoProductException;
import com.accenture_project.order.mappers.ProductsMapper;
import com.accenture_project.order.models.ProductModel;
import com.accenture_project.order.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    private final ProductsMapper productsMapper;

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

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") UUID id) {
        try {
            productService.deleteById(id);

            return ResponseEntity.ok().body("Product successfully deleted");
        } catch (NoProductException e) {
            logger.error("Error retrieving product ", e);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error deleting product");
        } catch (Exception e) {
            logger.error("Error deleting product ", e);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Error deleting product");
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") UUID id, @RequestBody ProductDTO productDTO) {
        try {
            var product = productsMapper.toProductModel(productDTO);

            productService.validateProduct(product);
            productService.updateProduct(id, productDTO);

            logger.info("Product successfully updated");

            return ResponseEntity.status(HttpStatus.OK).body("Product updated successfully!");
        } catch (InvalidProductException e) {
            logger.error("Error updating product", e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error updating product", e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unexpected error: " + e.getMessage());
        }
    }
}