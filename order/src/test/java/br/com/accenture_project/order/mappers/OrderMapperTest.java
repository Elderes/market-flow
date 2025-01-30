package br.com.accenture_project.order.mappers;

import br.com.accenture_project.order.dtos.OrderDTO;
import br.com.accenture_project.order.models.ClientModel;
import br.com.accenture_project.order.models.OrderModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderMapperTest {

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private OrderMapper orderMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToOrderModel() {
        // Arrange
        OrderDTO orderDTO = mock(OrderDTO.class);
        ClientModel clientModel = new ClientModel();
        when(clientMapper.toClientModel(any())).thenReturn(clientModel);

        OrderModel result = orderMapper.toOrderModel(orderDTO);

        assertNotNull(result);
        assertEquals(clientModel, result.getClient());
        assertNotNull(result.getOrderDateTime());
        assertTrue(result.getOrderDateTime().isBefore(LocalDateTime.now().plusSeconds(1)));
    }
}
