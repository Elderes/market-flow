package br.com.accenture_project.order.mappers;

import br.com.accenture_project.order.dtos.AddressDTO;
import br.com.accenture_project.order.dtos.ClientDTO;
import br.com.accenture_project.order.models.AddressModel;
import br.com.accenture_project.order.models.ClientModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientMapperTest {

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private ClientMapper clientMapper;

    private ClientDTO clientDTO;
    private ClientModel clientModel;
    private AddressDTO addressDTO;
    private AddressModel addressModel;

    @BeforeEach
    public void setUp() {
        addressDTO = new AddressDTO(
                "Brasil",
                "Paraíba",
                "Esperança",
                "Centro",
                "Rua X",
                100
        );

        addressModel = new AddressModel();
        addressModel.setCountry("Brasil");
        addressModel.setState("Paraíba");
        addressModel.setCity("Campina Grande");
        addressModel.setNeighborhood("Centro");
        addressModel.setStreet("Rua Y");
        addressModel.setNumber(100);

        clientDTO = new ClientDTO(
                "Lucas",
                "83991546906",
                "lucazmatehus14@example.com",
                addressDTO
        );

        clientModel = new ClientModel();
        clientModel.setName("Lucas");
        clientModel.setCellphone("83991546906");
        clientModel.setEmail("lucazmatehus14@example.com");
        clientModel.setAddress(addressModel);
    }

    @Test
    public void testToClientModel() {
        when(addressMapper.toAddressModel(clientDTO.address())).thenReturn(addressModel);

        ClientModel result = clientMapper.toClientModel(clientDTO);

        assertNotNull(result);
        assertEquals(clientDTO.name(), result.getName());
        assertEquals(clientDTO.cellphone(), result.getCellphone());
        assertEquals(clientDTO.email(), result.getEmail());
        assertEquals(addressModel, result.getAddress());

        verify(addressMapper, times(1)).toAddressModel(clientDTO.address());
    }

    @Test
    public void testToClientDTO() {
        when(addressMapper.toAddressDTO(clientModel.getAddress())).thenReturn(addressDTO);

        ClientDTO result = clientMapper.toClientDTO(clientModel);

        assertNotNull(result);
        assertEquals(clientModel.getName(), result.name());
        assertEquals(clientModel.getCellphone(), result.cellphone());
        assertEquals(clientModel.getEmail(), result.email());
        assertEquals(addressDTO, result.address());

        verify(addressMapper, times(1)).toAddressDTO(clientModel.getAddress());
    }
}
