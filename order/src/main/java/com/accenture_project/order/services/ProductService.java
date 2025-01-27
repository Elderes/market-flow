package com.accenture_project.order.services;

import com.accenture_project.order.exceptions.InvalidProductException;
import com.accenture_project.order.models.ProductModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    public void validateProducts(List<ProductModel> products) {
        for (int i = 1; i <= products.size(); i++) {
            if (products.get(i - 1).getName().isBlank()) {
                throw new InvalidProductException("O nome do produto " + i + " está vazio.");
            }

            if (products.get(i - 1).getQuantity() < 1) {
                throw new InvalidProductException("O produto " + products.get(i - 1).getName() + " está com quantidade menor que 1.");
            }

            if (products.get(i - 1).getUnitPrice().compareTo(BigDecimal.ZERO) < 0) {
                throw new InvalidProductException("O produto " + products.get(i - 1).getName() + " está com o preço unitário menor que 0.");
            }
        }
    }
}
