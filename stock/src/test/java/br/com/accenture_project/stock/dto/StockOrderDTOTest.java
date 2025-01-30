package br.com.accenture_project.stock.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import br.com.accenture_project.stock.model.Product;
import org.junit.jupiter.api.Test;

class StockOrderDTOTest {

    @Test
    void testStockOrderDTOValid() {
        UUID orderId = UUID.randomUUID();
        List<Product> products = List.of(new Product(UUID.randomUUID(), "Product A", 2, BigDecimal.valueOf(10.50)));
        boolean approval = true;

        StockOrderDTO stockOrder = new StockOrderDTO();
        stockOrder.setOrderId(orderId);
        stockOrder.setProducts(products);
        stockOrder.setApproval(approval);

        assertEquals(orderId, stockOrder.getOrderId());
        assertEquals(products, stockOrder.getProducts());
        assertTrue(stockOrder.isApproval());
    }

    @Test
    void testStockOrderDTONullValues() {
        StockOrderDTO stockOrderDTO = new StockOrderDTO(null, null, false);

        assertNull(stockOrderDTO.getOrderId());
        assertNull(stockOrderDTO.getProducts());
        assertFalse(stockOrderDTO.isApproval());
    }
}
