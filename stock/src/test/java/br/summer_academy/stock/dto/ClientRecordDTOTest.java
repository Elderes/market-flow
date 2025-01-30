package br.summer_academy.stock.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import java.util.Set;
import java.util.UUID;

class ClientRecordDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testClientRecordDTONullValues() {
        ClientRecordDTO clientRecordDTO =  new ClientRecordDTO(null, null, null, null, null);
        assertNull(clientRecordDTO.id());
        assertNull(clientRecordDTO.name());
        assertNull(clientRecordDTO.cellphone());
        assertNull(clientRecordDTO.email());
        assertNull(clientRecordDTO.address());
    }


    @Test
    void testClientRecordDTOValidValues() {
        AddressRecordDTO address = new AddressRecordDTO(UUID.randomUUID(), "Brazil", "SP", "São Paulo", "Centro", "Rua A", "123");
        ClientRecordDTO client = new ClientRecordDTO(UUID.randomUUID(), "John Doe", "999999999", "john@example.com", address);
        Set<ConstraintViolation<ClientRecordDTO>> violations = validator.validate(client);
        assertTrue(violations.isEmpty(), "Validation should pass for valid values");
    }
}
