package br.com.accenture_project.status.services;

import br.com.accenture_project.status.dtos.*;
import br.com.accenture_project.status.exceptions.NoStatusException;
import br.com.accenture_project.status.mappers.StatusMapper;
import br.com.accenture_project.status.models.StatusModel;
import br.com.accenture_project.status.producers.StatusProducer;
import br.com.accenture_project.status.repositories.StatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StatusServiceTest {

    @Mock
    private StatusRepository statusRepository;

    @Mock
    private StatusProducer statusProducer;

    @Mock
    private StatusMapper statusMapper;

    @InjectMocks
    private StatusService statusService;

    private OrderDTO orderDTO;
    private PaymentDTO paymentDTO;
    private StatusModel statusModel;

    @BeforeEach
    public void setUp() {
        orderDTO = new OrderDTO(
                UUID.randomUUID(),
                new ClientDTO(
                        UUID.randomUUID(),
                        "John Doe",
                        "11999999999",
                        "john.doe@example.com",
                        new AddressDTO(
                                UUID.randomUUID(),
                                "Brasil",
                                "São Paulo",
                                "São Paulo",
                                "Centro",
                                "Rua Augusta",
                                100
                        )
                ),
                List.of(
                        new ProductDTO(
                                UUID.randomUUID(),
                                "Product 1",
                                10,
                                BigDecimal.valueOf(100.0)
                        )
                ),
                LocalDateTime.now()
        );

        paymentDTO = new PaymentDTO(
                BigDecimal.valueOf(100.0),
                LocalDateTime.now(),
                orderDTO.id(),
                true
        );

        statusModel = new StatusModel();
        statusModel.setId(UUID.randomUUID());
        statusModel.setOrderId(orderDTO.id());
        statusModel.setWasPaid(false);
        statusModel.setLastUpdate(LocalDateTime.now());
        statusModel.setEmailClient(orderDTO.client().email());
    }

    @Test
    public void testSaveOrderStatus() {
        statusService.saveOrderStatus(orderDTO);

        verify(statusRepository, times(1)).save(any(StatusModel.class));
    }

    @Test
    public void testPaymentOrder_Success() {
        when(statusRepository.findByOrderId(paymentDTO.orderId())).thenReturn(Optional.of(statusModel));

        statusService.paymentOrder(paymentDTO);

        assertTrue(statusModel.isWasPaid());
        assertEquals(paymentDTO.totalPrice(), statusModel.getTotalPrice());
        verify(statusRepository, times(1)).save(statusModel);
        verify(statusProducer, times(1)).publishOrder(any());
    }

    @Test
    public void testPaymentOrder_OrderNotFound_ThrowsException() {
        when(statusRepository.findByOrderId(paymentDTO.orderId())).thenReturn(Optional.empty());

        assertThrows(NoStatusException.class, () -> statusService.paymentOrder(paymentDTO));
    }

    @Test
    public void testGetAllStatus_Success() {
        when(statusRepository.findAll()).thenReturn(List.of(statusModel));

        List<StatusModel> statusList = statusService.getAllStatus();

        assertFalse(statusList.isEmpty());
        assertEquals(1, statusList.size());
        assertEquals(statusModel, statusList.get(0));
    }

    @Test
    public void testGetAllStatus_NoStatus_ThrowsException() {
        when(statusRepository.findAll()).thenReturn(List.of());

        assertThrows(NoStatusException.class, () -> statusService.getAllStatus());
    }

    @Test
    public void testGetStatus_Success() {
        UUID id = UUID.randomUUID();
        when(statusRepository.findById(id)).thenReturn(Optional.of(statusModel));

        StatusModel foundStatus = statusService.getStatus(id);

        assertNotNull(foundStatus);
        assertEquals(statusModel, foundStatus);
    }

    @Test
    public void testGetStatus_NotFound_ThrowsException() {
        UUID id = UUID.randomUUID();
        when(statusRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoStatusException.class, () -> statusService.getStatus(id));
    }

    @Test
    public void testDeleteById_Success() {
        UUID id = UUID.randomUUID();
        when(statusRepository.findById(id)).thenReturn(Optional.of(statusModel));

        statusService.deleteById(id);

        verify(statusRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteById_NotFound_ThrowsException() {
        UUID id = UUID.randomUUID();
        when(statusRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoStatusException.class, () -> statusService.deleteById(id));
    }
}