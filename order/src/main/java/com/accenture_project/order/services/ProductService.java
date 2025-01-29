package com.accenture_project.order.services;

import com.accenture_project.order.dtos.ProductDTO;
import com.accenture_project.order.exceptions.InvalidProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/*
 * ProductService Class
 *
 * This class provides services for product validation. It checks the properties of
 * each product in a list, ensuring that the product name is not empty, the quantity
 * is greater than 0, and the unit price is not negative.
 *
 * Key method:
 * - validateProducts(List<ProductDTO> products): Validates a list of products by checking
 *   the product name, quantity, and unit price. Throws an InvalidProductException if any
 *   of the conditions are not met.
 */

@RequiredArgsConstructor
@Service
public class ProductService {

    public void validateProducts(List<ProductDTO> products) {
        for (int i = 1; i <= products.size(); i++) {
            if (products.get(i - 1).name().isBlank()) {
                throw new InvalidProductException("The name of the product " + i + " is empty.");
            }

            if (products.get(i - 1).quantity() < 1) {
                throw new InvalidProductException("The product " + products.get(i - 1).name() + " has a quantity less than 1.");
            }

            if (products.get(i - 1).unitPrice().compareTo(BigDecimal.ZERO) < 0) {
                throw new InvalidProductException("The product " + products.get(i - 1).name() + " has a unit price lower than 0.");
            }
        }
    }
}
