package com.accenture_projeto.buyer.repositories;

import com.accenture_projeto.buyer.models.AddressModel;
import com.accenture_projeto.buyer.models.BuyerModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BuyerRepositoryTest {
//
//    @Autowired
//    private BuyerRepository buyerRepository;
//
//    @Test
//    public void testSaveBuyer() {
//        var buyer = new BuyerModel();
//        var address = new AddressModel();
//        buyer.setName("John Doe");
//        buyer.setEmail("john.doe@gmail.com");
//        buyer.setCellphone("00123456789");
//
//        address.setCountry("Brasil");
//        address.setState("Paraíba");
//        address.setCity("Esperança");
//        address.setNeighborhood("Centro");
//        address.setStreet("Rua tal");
//        address.setNumber(1234);
//
//        buyer.setAddress(address);
//
//        var savedBuyer = buyerRepository.save(buyer);
//
//        assertNotNull(savedBuyer.getId());
//        assertEquals("John Doe", savedBuyer.getName());
//        assertEquals("john.doe@gmail.com", savedBuyer.getEmail());
//    }
}
