package br.summer_academy.stock.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class ProductRecordDTOTest {

    @Test
    void testProductRecordDTO() {
        UUID id = UUID.randomUUID();
        String name = "Product Test";
        Integer quantity = 10;
        BigDecimal unitPrice = new BigDecimal("29.99");

        ProductRecordDTO product = new ProductRecordDTO(id, name, quantity, unitPrice);

        assertEquals(id, product.id());
        assertEquals(name, product.name());
        assertEquals(quantity, product.quantity());
        assertEquals(unitPrice, product.unitPrice());
    }

    @Test
    void testProductRecordDTONullValues() {
        ProductRecordDTO productRecordDTO = new ProductRecordDTO(null, null, null, null);

        assertNull(productRecordDTO.id());
        assertNull(productRecordDTO.name());
        assertNull(productRecordDTO.quantity());
        assertNull(productRecordDTO.unitPrice());
    }
}
