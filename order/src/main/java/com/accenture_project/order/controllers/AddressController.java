package com.accenture_project.order.controllers;

import com.accenture_project.order.dtos.AddressDTO;
import com.accenture_project.order.exceptions.InvalidAddressException;
import com.accenture_project.order.exceptions.NoAddressException;
import com.accenture_project.order.mappers.AddressMapper;
import com.accenture_project.order.models.AddressModel;
import com.accenture_project.order.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/*
 * This class handles HTTP requests related to addresses.
 * It provides the following endpoints:
 *
 * - GET /addresses: Retrieves a list of all addresses.
 * - GET /address/{id}: Retrieves a specific address by its ID.
 * - PUT /address/{id}: Updates an existing address based on the provided ID and address data.
 */

@RequiredArgsConstructor
@RestController
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressModel>> getAddresses() {
        try {
            var addresses = addressService.getAllAddresses();

            return ResponseEntity.ok().body(addresses);
        } catch (NoAddressException e) {
            logger.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving addresses", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<AddressModel> getAddressById(@PathVariable("id") UUID id) {
        try {
            var address = addressService.getAddress(id);

            return ResponseEntity.ok().body(address);
        } catch (NoAddressException e) {
            logger.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving address", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<String> updateClient(@PathVariable("id") UUID id, @RequestBody AddressDTO addressDTO) {
        try {
            var address = addressMapper.toAddressModel(addressDTO);

            addressService.validateAddress(address);
            addressService.updateAddress(id, addressDTO);

            logger.info("Address successfully updated");

            return ResponseEntity.status(HttpStatus.OK).body("Address updated successfully!");
        } catch (InvalidAddressException e) {
            logger.error("Error updating address", e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error updating address", e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unexpected error: " + e.getMessage());
        }
    }
}
