package com.accenture_project.order.mappers;

import com.accenture_project.order.dtos.AddressDTO;
import com.accenture_project.order.dtos.ClientDTO;
import com.accenture_project.order.dtos.OrderDTO;
import com.accenture_project.order.models.AddressModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AddressUpdateMapperTest {

    private AddressUpdateMapper addressUpdateMapper;

    @BeforeEach
    void setUp() {
        addressUpdateMapper = new AddressUpdateMapper();
    }

    @Test
    void givenAddressDTO_whenToAddressModel_thenMapsCorrectly() {
        // Arrange
        AddressModel addressModel = new AddressModel(UUID.randomUUID(), "Brasil", "São Paulo", "São Paulo", "Centro", "Rua Augusta", 100);
        AddressDTO addressDTO = new AddressDTO("Argentina", "Buenos Aires", "Buenos Aires", "Palermo", "Calle Florida", 200);

        // Act
        AddressModel updatedAddress = addressUpdateMapper.toAddressModel(addressModel, addressDTO);

        // Assert
        assertAll(
                () -> assertNotNull(updatedAddress),
                () -> assertEquals(addressDTO.country(), updatedAddress.getCountry()),
                () -> assertEquals(addressDTO.state(), updatedAddress.getState()),
                () -> assertEquals(addressDTO.city(), updatedAddress.getCity()),
                () -> assertEquals(addressDTO.neighborhood(), updatedAddress.getNeighborhood()),
                () -> assertEquals(addressDTO.street(), updatedAddress.getStreet()),
                () -> assertEquals(addressDTO.number(), updatedAddress.getNumber())
        );
    }

    @Test
    void givenClientDTO_whenToAddressModel_thenMapsCorrectly() {
        // Arrange
        AddressModel addressModel = new AddressModel(UUID.randomUUID(), "Brasil", "Paraíba", "Esperança", "Centro", "Rua X", 100);
        ClientDTO clientDTO = new ClientDTO("Lucas", "83991546906", "lucazmatehus14@gmail.com", new AddressDTO("Brasil", "Paraíba", "Campina Grande", "Centro", "Rua y", 300));

        // Act
        AddressModel updatedAddress = addressUpdateMapper.toAddressModel(addressModel, clientDTO);

        // Assert
        assertAll(
                () -> assertNotNull(updatedAddress),
                () -> assertEquals(clientDTO.address().country(), updatedAddress.getCountry()),
                () -> assertEquals(clientDTO.address().state(), updatedAddress.getState()),
                () -> assertEquals(clientDTO.address().city(), updatedAddress.getCity()),
                () -> assertEquals(clientDTO.address().neighborhood(), updatedAddress.getNeighborhood()),
                () -> assertEquals(clientDTO.address().street(), updatedAddress.getStreet()),
                () -> assertEquals(clientDTO.address().number(), updatedAddress.getNumber())
        );
    }

    @Test
    void givenOrderDTO_whenToAddressModel_thenMapsCorrectly() {
        // Arrange
        AddressModel addressModel = new AddressModel(UUID.randomUUID(), "Brasil", "Paraíba", "Campina Grande", "Centro", "Rua W", 100);
        OrderDTO orderDTO = new OrderDTO(new ClientDTO("Matheus", "83991546906", "lucazmatehus14@gmail.com", new AddressDTO("Brasil", "Paraíba", "Esperança", "Centro", "Rua A", 400)), List.of());

        // Act
        AddressModel updatedAddress = addressUpdateMapper.toAddressModel(addressModel, orderDTO);

        // Assert
        assertAll(
                () -> assertNotNull(updatedAddress),
                () -> assertEquals(orderDTO.client().address().country(), updatedAddress.getCountry()),
                () -> assertEquals(orderDTO.client().address().state(), updatedAddress.getState()),
                () -> assertEquals(orderDTO.client().address().city(), updatedAddress.getCity()),
                () -> assertEquals(orderDTO.client().address().neighborhood(), updatedAddress.getNeighborhood()),
                () -> assertEquals(orderDTO.client().address().street(), updatedAddress.getStreet()),
                () -> assertEquals(orderDTO.client().address().number(), updatedAddress.getNumber())
        );
    }
}
