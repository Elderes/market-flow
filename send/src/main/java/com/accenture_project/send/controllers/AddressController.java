package com.accenture_project.send.controllers;

import com.accenture_project.send.exceptions.NoAddressException;
import com.accenture_project.send.models.AddressModel;
import com.accenture_project.send.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * This controller provides endpoints for retrieving clients.
 *
 * - GET /clients: Fetches a list of all clients.
 * - GET /client/{id}: Fetches a specific client by their ID.
 *
 * Exceptions:
 * - Handles NoClientException by logging and returning a 500 Internal Server Error.
 * - Handles general exceptions by logging and returning a 500 Internal Server Error.
 */

@RequiredArgsConstructor
@RestController
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    private final AddressService addressService;

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
}
