package com.accenture_project.order.controllers;

import com.accenture_project.order.dtos.ClientDTO;
import com.accenture_project.order.dtos.OrderDTO;
import com.accenture_project.order.exceptions.*;
import com.accenture_project.order.mappers.ClientMapper;
import com.accenture_project.order.models.ClientModel;
import com.accenture_project.order.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final ClientService clientService;

    private final ClientMapper clientMapper;

    @GetMapping("/clients")
    public ResponseEntity<List<ClientModel>> getClients() {
        try {
            var clients = clientService.getAllClients();

            return ResponseEntity.ok().body(clients);
        } catch (NoClientException e) {
            logger.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving clients", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<ClientModel> getClientById(@PathVariable("id") UUID id) {
        try {
            var client = clientService.getClient(id);

            return ResponseEntity.ok().body(client);
        } catch (NoClientException e) {
            logger.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving client", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") UUID id) {
        try {
            clientService.deleteById(id);

            return ResponseEntity.ok().body("Client successfully deleted");
        } catch (NoClientException e) {
            logger.error("Error retrieving client", e);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error deleting client");
        } catch (Exception e) {
            logger.error("Error deleting client", e);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Error deleting client");
        }
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<String> updateClient(@PathVariable("id") UUID id, @RequestBody ClientDTO clientDTO) {
        try {
            var client = clientMapper.toClientModel(clientDTO);

            clientService.validateClient(client);
            clientService.updateClient(id, clientDTO);

            logger.info("Client successfully updated");

            return ResponseEntity.status(HttpStatus.OK).body("Client updated successfully!");
        } catch (InvalidClientException | InvalidAddressException e) {
            logger.error("Error updating client", e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error updating client", e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unexpected error: " + e.getMessage());
        }
    }
}
