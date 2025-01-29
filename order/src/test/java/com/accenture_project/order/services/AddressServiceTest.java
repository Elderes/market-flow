package com.accenture_project.order.services;

import com.accenture_project.order.dtos.AddressDTO;
import com.accenture_project.order.exceptions.InvalidAddressException;
import com.accenture_project.order.exceptions.NoAddressException;
import com.accenture_project.order.mappers.AddressUpdateMapper;
import com.accenture_project.order.models.AddressModel;
import com.accenture_project.order.repositories.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressUpdateMapper addressUpdateMapper;

    @InjectMocks
    private AddressService addressService;

    private AddressModel validAddress;
    private AddressDTO addressDTO;

    @BeforeEach
    public void setUp() {
        validAddress = new AddressModel();
        validAddress.setCountry("Brasil");
        validAddress.setState("Paraíba");
        validAddress.setCity("Esperança");
        validAddress.setNeighborhood("Centro");
        validAddress.setStreet("Rua x");
        validAddress.setNumber(100);

        addressDTO = new AddressDTO("Brasil",
                "Paraíba",
                "Esperança",
                "Centro",
                "Rua x",
                1);
    }

    @Test
    public void testValidateAddressCase1() {
        assertDoesNotThrow(() -> addressService.validateAddress(validAddress));
    }

    @Test
    public void testValidateAddressCase2() {
        validAddress.setCountry("");
        assertThrows(InvalidAddressException.class, () -> addressService.validateAddress(validAddress));
    }

    @Test
    public void testValidateAddressCase3() {
        validAddress.setState("");
        assertThrows(InvalidAddressException.class, () -> addressService.validateAddress(validAddress));
    }

    @Test
    public void testValidateAddressCase4() {
        validAddress.setCity("");
        assertThrows(InvalidAddressException.class, () -> addressService.validateAddress(validAddress));
    }

    @Test
    public void testValidateAddressCase5() {
        validAddress.setNeighborhood("");
        assertThrows(InvalidAddressException.class, () -> addressService.validateAddress(validAddress));
    }

    @Test
    public void testValidateAddressCase6() {
        validAddress.setStreet("");
        assertThrows(InvalidAddressException.class, () -> addressService.validateAddress(validAddress));
    }

    @Test
    public void testValidateAddressCase7() {
        validAddress.setNumber(0);
        assertThrows(InvalidAddressException.class, () -> addressService.validateAddress(validAddress));
    }

    @Test
    public void testGetAllAddressesCase8() {
        when(addressRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(NoAddressException.class, () -> addressService.getAllAddresses());
    }

    @Test
    public void testGetAllAddressesCase9() {
        when(addressRepository.findAll()).thenReturn(List.of(validAddress));
        List<AddressModel> addresses = addressService.getAllAddresses();
        assertFalse(addresses.isEmpty());
        assertEquals(1, addresses.size());
    }

    @Test
    public void testGetAddressCase10() {
        UUID id = UUID.randomUUID();
        when(addressRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NoAddressException.class, () -> addressService.getAddress(id));
    }

    @Test
    public void testGetAddressCase11() {
        UUID id = UUID.randomUUID();
        when(addressRepository.findById(id)).thenReturn(Optional.of(validAddress));
        AddressModel address = addressService.getAddress(id);
        assertNotNull(address);
        assertEquals(validAddress, address);
    }

    @Test
    public void testUpdateAddressCase12() {
        UUID id = UUID.randomUUID();
        when(addressRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NoAddressException.class, () -> addressService.updateAddress(id, addressDTO));
    }

    @Test
    public void testUpdateAddressCase13() {
        UUID id = UUID.randomUUID();
        when(addressRepository.findById(id)).thenReturn(Optional.of(validAddress));
        when(addressUpdateMapper.toAddressModel(validAddress, addressDTO)).thenReturn(validAddress);

        addressService.updateAddress(id, addressDTO);

        verify(addressRepository, times(1)).save(validAddress);
    }
}