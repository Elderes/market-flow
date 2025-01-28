package com.accenture_project.order.services;

import com.accenture_project.order.dtos.ProductDTO;
import com.accenture_project.order.exceptions.InvalidProductException;
import com.accenture_project.order.exceptions.NoAddressException;
import com.accenture_project.order.exceptions.NoProductException;
import com.accenture_project.order.mappers.ProductsUpdateMapper;
import com.accenture_project.order.models.ProductModel;
import com.accenture_project.order.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductsUpdateMapper productsUpdateMapper;

    public void validateProducts(List<ProductModel> products) {
        for (int i = 1; i <= products.size(); i++) {
            if (products.get(i - 1).getName().isBlank()) {
                throw new InvalidProductException("The name of the product " + i + " is empty.");
            }

            if (products.get(i - 1).getQuantity() < 1) {
                throw new InvalidProductException("The product " + products.get(i - 1).getName() + " has a quantity less than 1.");
            }

            if (products.get(i - 1).getUnitPrice().compareTo(BigDecimal.ZERO) < 0) {
                throw new InvalidProductException("The product " + products.get(i - 1).getName() + " has a unit price lower than 0.");
            }
        }
    }

    public void validateProduct(ProductModel product) {
        if (product.getName().isBlank()) {
            throw new InvalidProductException("Product name is empty");
        }

        if (product.getQuantity() < 1) {
            throw new InvalidProductException("The product " + product.getName() + " has a quantity less than 1.");
        }

        if (product.getUnitPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidProductException("The product " + product.getName() + " has a unit price lower than 0.");
        }
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

    public void deleteById(UUID id) {
        if (getProduct(id) == null) {
            throw new NoProductException("Product not found with id:" + id);
        }
        productRepository.deleteById(id);
    }


    public void updateProduct(UUID id, ProductDTO productDTO) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new NoProductException("Product not found"));

        product = productsUpdateMapper.toProductModel(product, productDTO);

        productRepository.save(product);
    }
}
