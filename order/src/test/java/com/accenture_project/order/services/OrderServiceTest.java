package com.accenture_project.order.services;

import com.accenture_project.order.dtos.OrderDTO;
import com.accenture_project.order.dtos.OrderProducerDTO;
import com.accenture_project.order.dtos.ProductDTO;
import com.accenture_project.order.exceptions.NoOrderException;
import com.accenture_project.order.mappers.OrderProducerMapper;
import com.accenture_project.order.mappers.OrderUpdateMapper;
import com.accenture_project.order.models.OrderModel;
import com.accenture_project.order.producers.OrderProducer;
import com.accenture_project.order.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderProducer orderProducer;

    @Mock
    private ClientService clientService;

    @Mock
    private ProductService productService;

    @Mock
    private OrderUpdateMapper orderUpdateMapper;

    @Mock
    private OrderProducerMapper orderProducerMapper;

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private OrderService orderService;

    private OrderModel order;
    private List<ProductDTO> products;

    @BeforeEach
    public void setUp() {
        order = new OrderModel();
        order.setId(UUID.randomUUID());

        products = List.of(
                new ProductDTO("Product 1", 10, BigDecimal.valueOf(100.0)),
                new ProductDTO("Product 2", 5, BigDecimal.valueOf(50.0))
        );
    }

    @Test
    public void testSaveOrder() {
        when(orderRepository.save(order)).thenReturn(order);
        OrderModel savedOrder = orderService.saveOrder(order);
        assertNotNull(savedOrder);
        assertEquals(order, savedOrder);
    }

    @Test
    public void testPublishOrder() {
        var message = new OrderProducerDTO(null, null, null, null); // Simula o DTO de mensagem
        when(orderProducerMapper.toOrderProducerDTO(order, products)).thenReturn(message);

        orderService.publishOrder(order, products);

        verify(orderProducer, times(1)).publishOrder(message);
    }

    @Test
    public void testValidateOrder() {
        orderService.validateOrder(order, products);

        verify(clientService, times(1)).validateClient(order.getClient());
        verify(productService, times(1)).validateProducts(products);
    }

    @Test
    public void testGetOrders_NoOrders_ThrowsException() {
        when(orderRepository.findAll()).thenReturn(List.of());

        assertThrows(NoOrderException.class, () -> orderService.getOrders());
    }

    @Test
    public void testGetOrders_WithOrders_ReturnsList() {
        when(orderRepository.findAll()).thenReturn(List.of(order));

        List<OrderModel> orders = orderService.getOrders();
        assertFalse(orders.isEmpty());
        assertEquals(1, orders.size());
    }

    @Test
    public void testGetOrder_NotFound_ThrowsException() {
        UUID id = UUID.randomUUID();
        when(orderRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoOrderException.class, () -> orderService.getOrder(id));
    }

    @Test
    public void testGetOrder_Found_ReturnsOrder() {
        UUID id = UUID.randomUUID();
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));

        OrderModel foundOrder = orderService.getOrder(id);
        assertNotNull(foundOrder);
        assertEquals(order, foundOrder);
    }

    @Test
    public void testDeleteById_NotFound_ThrowsException() {
        UUID id = UUID.randomUUID();
        when(orderRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoOrderException.class, () -> orderService.deleteById(id));
    }

    @Test
    public void testDeleteById_Found_DeletesOrder() {
        UUID id = UUID.randomUUID();
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));

        orderService.deleteById(id);

        verify(orderRepository, times(1)).deleteById(id);
    }

    @Test
    public void testUpdateOrder_NotFound_ThrowsException() {
        UUID id = UUID.randomUUID();
        when(orderRepository.findById(id)).thenReturn(Optional.empty());

        OrderDTO orderDTO = new OrderDTO(null, null);
        assertThrows(NoOrderException.class, () -> orderService.updateOrder(id, orderDTO));
    }
}