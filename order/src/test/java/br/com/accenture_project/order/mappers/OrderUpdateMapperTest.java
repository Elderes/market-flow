package br.com.accenture_project.order.mappers;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.accenture_project.order.dtos.OrderDTO;
import br.com.accenture_project.order.models.ClientModel;
import br.com.accenture_project.order.models.OrderModel;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
public class OrderUpdateMapperTest {

    @Mock
    private ClientUpdateMapper clientUpdateMapper;

    @InjectMocks
    private OrderUpdateMapper orderUpdateMapper;

    @Test
    public void testToOrderModel() {
        // Arrange
        OrderModel orderModel = new OrderModel();
        orderModel.setId(UUID.randomUUID());
        orderModel.setOrderDateTime(LocalDateTime.now());
        ClientModel clientModel = new ClientModel();
        orderModel.setClient(clientModel);

        OrderDTO orderDTO = new OrderDTO(null, null);

        ClientModel updatedClientModel = new ClientModel();

        when(clientUpdateMapper.toClientModel(clientModel, orderDTO)).thenReturn(updatedClientModel);

        // Act
        OrderModel result = orderUpdateMapper.toOrderModel(orderModel, orderDTO);

        // Assert
        assertNotNull(result);
        assertEquals(updatedClientModel, result.getClient());
        verify(clientUpdateMapper).toClientModel(clientModel, orderDTO);
    }
}
