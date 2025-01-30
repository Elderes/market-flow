package br.com.accenture_project.order.services;

import br.com.accenture_project.order.dtos.ProductDTO;
import br.com.accenture_project.order.exceptions.InvalidProductException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    public void setUp() {
        productService = new ProductService();
    }

    @Test
    public void testValidateProducts_ValidProducts_NoExceptionThrown() {
        List<ProductDTO> validProducts = List.of(
                new ProductDTO("Product 1", 10, BigDecimal.valueOf(100.0)),
                new ProductDTO("Product 2", 5, BigDecimal.valueOf(50.0))
        );

        assertDoesNotThrow(() -> productService.validateProducts(validProducts));
    }

    @Test
    public void testValidateProducts_EmptyName_ThrowsException() {
        List<ProductDTO> invalidProducts = List.of(
                new ProductDTO("", 10, BigDecimal.valueOf(100.0))
        );

        InvalidProductException exception = assertThrows(InvalidProductException.class, () -> {
            productService.validateProducts(invalidProducts);
        });

        assertEquals("The name of the product 1 is empty.", exception.getMessage());
    }

    @Test
    public void testValidateProducts_QuantityLessThan1_ThrowsException() {
        List<ProductDTO> invalidProducts = List.of(
                new ProductDTO("Product 1", 0, BigDecimal.valueOf(100.0))
        );

        InvalidProductException exception = assertThrows(InvalidProductException.class, () -> {
            productService.validateProducts(invalidProducts);
        });

        assertEquals("The product Product 1 has a quantity less than 1.", exception.getMessage());
    }

    @Test
    public void testValidateProducts_UnitPriceLessThan0_ThrowsException() {
        List<ProductDTO> invalidProducts = List.of(
                new ProductDTO("Product 1", 10, BigDecimal.valueOf(-1.0))
        );

        InvalidProductException exception = assertThrows(InvalidProductException.class, () -> {
            productService.validateProducts(invalidProducts);
        });

        assertEquals("The product Product 1 has a unit price lower than 0.", exception.getMessage());
    }

    @Test
    public void testValidateProducts_MultipleInvalidProducts_ThrowsExceptionForFirstInvalid() {
        List<ProductDTO> invalidProducts = List.of(
                new ProductDTO("", 10, BigDecimal.valueOf(100.0)),
                new ProductDTO("Product 2", 0, BigDecimal.valueOf(50.0)),
                new ProductDTO("Product 3", 5, BigDecimal.valueOf(-10.0))
        );

        InvalidProductException exception = assertThrows(InvalidProductException.class, () -> {
            productService.validateProducts(invalidProducts);
        });

        assertEquals("The name of the product 1 is empty.", exception.getMessage());
    }
}