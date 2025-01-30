package br.com.accenture_project.order.mappers;

import br.com.accenture_project.order.dtos.AddressDTO;
import br.com.accenture_project.order.dtos.ClientDTO;
import br.com.accenture_project.order.dtos.OrderDTO;
import br.com.accenture_project.order.models.AddressModel;
import br.com.accenture_project.order.models.ClientModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientUpdateMapperTest {

    @Mock
    private AddressUpdateMapper addressUpdateMapper;

    @InjectMocks
    private ClientUpdateMapper clientUpdateMapper;

    private ClientModel clientModel;
    private ClientDTO clientDTO;
    private OrderDTO orderDTO;
    private AddressModel addressModel;

    @BeforeEach
    public void setUp() {
        addressModel = new AddressModel();
        addressModel.setCountry("Brasil");
        addressModel.setState("Paraíba");
        addressModel.setCity("Esperança");
        addressModel.setNeighborhood("Centro");
        addressModel.setStreet("Rua X");
        addressModel.setNumber(100);

        clientModel = new ClientModel();
        clientModel.setName("Lucas");
        clientModel.setCellphone("83991546906");
        clientModel.setEmail("lucazmatehus14@gmail.com");
        clientModel.setAddress(addressModel);

        clientDTO = new ClientDTO(
                "Matheus",
                "83991546906",
                "lucazmatehus14@gmail.com",
                new AddressDTO(
                        "Brasil",
                        "Paraíba",
                        "Campina Grande",
                        "Centro",
                        "Rua y",
                        200
                )
        );

        orderDTO = new OrderDTO(
                new ClientDTO(
                        "Lima",
                        "83991546906",
                        "lucazmatehus14@gmail.com",
                        new AddressDTO(
                                "Brasil",
                                "Paraíba",
                                "João Pessoa",
                                "Centro",
                                "Rua y",
                                300
                        )
                ),
                List.of()
        );
    }

    @Test
    public void testToClientModel_FromClientDTO() {
        when(addressUpdateMapper.toAddressModel(clientModel.getAddress(), clientDTO)).thenReturn(addressModel);

        ClientModel result = clientUpdateMapper.toClientModel(clientModel, clientDTO);

        assertNotNull(result);
        assertEquals(clientDTO.name(), result.getName());
        assertEquals(clientDTO.cellphone(), result.getCellphone());
        assertEquals(clientDTO.email(), result.getEmail());
        assertEquals(addressModel, result.getAddress());

        verify(addressUpdateMapper, times(1)).toAddressModel(clientModel.getAddress(), clientDTO);
    }

    @Test
    public void testToClientModel_FromOrderDTO() {
        when(addressUpdateMapper.toAddressModel(clientModel.getAddress(), orderDTO)).thenReturn(addressModel);

        ClientModel result = clientUpdateMapper.toClientModel(clientModel, orderDTO);

        assertNotNull(result);
        assertEquals(orderDTO.client().name(), result.getName());
        assertEquals(orderDTO.client().cellphone(), result.getCellphone());
        assertEquals(orderDTO.client().email(), result.getEmail());
        assertEquals(addressModel, result.getAddress());

        verify(addressUpdateMapper, times(1)).toAddressModel(clientModel.getAddress(), orderDTO);
    }
}
