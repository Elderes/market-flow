package br.com.accenture_project.send.controllers;

import br.com.accenture_project.send.exceptions.SendNotFoundException;
import br.com.accenture_project.send.models.SendModel;
import br.com.accenture_project.send.services.SendService;
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

/*
 * SendController Class
 *
 * Handles HTTP requests related to the "Send" entity.
 * Provides endpoints for retrieving all sends and finding a specific send by its ID.
 * Returns appropriate HTTP responses for successful retrieval or error scenarios.
 */

@RequiredArgsConstructor
@RestController
public class SendController {

    private static final Logger logger = LoggerFactory.getLogger(SendController.class);

    private final SendService sendService;

    @GetMapping("/sends")
    public ResponseEntity<List<SendModel>> getSends() {
        try {
            var sends = sendService.getAllSends();

            return ResponseEntity.ok().body(sends);
        } catch (SendNotFoundException e) {
            logger.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving sends", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/send/{id}")
    public ResponseEntity<SendModel> getSendById(@PathVariable("id") UUID id) {
        try {
            var send = sendService.findById(id);

            return ResponseEntity.ok().body(send);
        } catch (SendNotFoundException e) {
            logger.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving send", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
