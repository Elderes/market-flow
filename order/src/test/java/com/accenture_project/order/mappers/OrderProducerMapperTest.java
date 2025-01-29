package com.accenture_project.order.mappers;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.accenture_project.order.dtos.ClientDTO;
import com.accenture_project.order.dtos.OrderProducerDTO;
import com.accenture_project.order.dtos.ProductDTO;
import com.accenture_project.order.models.ClientModel;
import com.accenture_project.order.models.OrderModel;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

@SpringBootTest
public class OrderProducerMapperTest {

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private OrderProducerMapper orderProducerMapper;

    @Test
    public void testToOrderProducerDTO() {
        // Arrange
        OrderModel orderModel = new OrderModel();
        orderModel.setId(UUID.randomUUID());
        orderModel.setClient(new ClientModel());
        orderModel.setOrderDateTime(LocalDateTime.now());

        ProductDTO productDTO = new ProductDTO("",1, BigDecimal.ONE);
        List<ProductDTO> productsDTO = new ArrayList<>();
        productsDTO.add(productDTO);

        ClientDTO clientDTO = new ClientDTO("", "", "", null);
        when(clientMapper.toClientDTO(orderModel.getClient())).thenReturn(clientDTO);

        // Act
        OrderProducerDTO result = orderProducerMapper.toOrderProducerDTO(orderModel, productsDTO);

        // Assert
        assertNotNull(result);
        assertEquals(orderModel.getId(), result.id());
        assertEquals(clientDTO, result.client());
        assertEquals(productsDTO, result.products());
        assertEquals(orderModel.getOrderDateTime(), result.orderDateTime());

        verify(clientMapper).toClientDTO(orderModel.getClient());
    }
}
