package com.accenture_project.order.mappers;

import com.accenture_project.order.dtos.AddressDTO;
import com.accenture_project.order.models.AddressModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressMapperTest {

    private AddressMapper addressMapper;

    @BeforeEach
    void setUp() {
        addressMapper = new AddressMapper();
    }

    @Test
    void toAddressModel_ShouldMapCorrectly() {
        // Arrange
        AddressDTO addressDTO = new AddressDTO(
                "Brasil",
                "Paraíba",
                "Esperança",
                "Centro",
                "Rua x",
                100
        );

        // Act
        AddressModel addressModel = addressMapper.toAddressModel(addressDTO);

        // Assert
        assertAll(
                () -> assertNotNull(addressModel),
                () -> assertEquals(addressDTO.country(), addressModel.getCountry()),
                () -> assertEquals(addressDTO.state(), addressModel.getState()),
                () -> assertEquals(addressDTO.city(), addressModel.getCity()),
                () -> assertEquals(addressDTO.neighborhood(), addressModel.getNeighborhood()),
                () -> assertEquals(addressDTO.street(), addressModel.getStreet()),
                () -> assertEquals(addressDTO.number(), addressModel.getNumber())
        );
    }

    @Test
    void toAddressDTO_ShouldMapCorrectly() {
        // Arrange
        AddressModel addressModel = new AddressModel();
        addressModel.setCountry("Brasil");
        addressModel.setState("Paraíba");
        addressModel.setCity("Campina Grande");
        addressModel.setNeighborhood("Centro");
        addressModel.setStreet("Rua u");
        addressModel.setNumber(200);

        // Act
        AddressDTO addressDTO = addressMapper.toAddressDTO(addressModel);

        // Assert
        assertAll(
                () -> assertNotNull(addressDTO),
                () -> assertEquals(addressModel.getCountry(), addressDTO.country()),
                () -> assertEquals(addressModel.getState(), addressDTO.state()),
                () -> assertEquals(addressModel.getCity(), addressDTO.city()),
                () -> assertEquals(addressModel.getNeighborhood(), addressDTO.neighborhood()),
                () -> assertEquals(addressModel.getStreet(), addressDTO.street()),
                () -> assertEquals(addressModel.getNumber(), addressDTO.number())
        );
    }
}
