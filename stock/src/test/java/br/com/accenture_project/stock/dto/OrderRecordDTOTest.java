package br.com.accenture_project.stock.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class OrderRecordDTOTest {

    @Test
    void testOrderRecordDTOValid() {
        UUID id = UUID.randomUUID();
        ClientRecordDTO client = new ClientRecordDTO(UUID.randomUUID(), "John Doe", "123456789", "john@example.com",
                new AddressRecordDTO(UUID.randomUUID(), "USA", "NY", "New York", "Brooklyn", "Main St", "100"));
        List<ProductRecordDTO> products = List.of(
                new ProductRecordDTO(UUID.randomUUID(), "Product A", 2, new BigDecimal(10.50)),
                new ProductRecordDTO(UUID.randomUUID(), "Product B", 1, new BigDecimal(20.00))
        );
        LocalDateTime orderDateTime = LocalDateTime.now();

        OrderRecordDTO order = new OrderRecordDTO(id, client, products, orderDateTime);

        assertEquals(id, order.id());
        assertEquals(client, order.client());
        assertEquals(products, order.products());
        assertEquals(orderDateTime, order.orderDateTime());
    }

    @Test
    void testOrderRecordDTONullValues() {
        OrderRecordDTO orderRecordDTO = new OrderRecordDTO(null, null, null, null);

        assertNull(orderRecordDTO.id());
        assertNull(orderRecordDTO.client());
        assertNull(orderRecordDTO.products());
        assertNull(orderRecordDTO.orderDateTime());
    }
}
