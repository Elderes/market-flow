package com.accenture_project.status.controllers;

import com.accenture_project.status.exceptions.NoStatusException;
import com.accenture_project.status.models.StatusModel;
import com.accenture_project.status.services.StatusService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class StatusController {

    private static final Logger logger = LoggerFactory.getLogger(StatusController.class);

    private final StatusService statusService;

    @GetMapping("/status")
    public ResponseEntity<List<StatusModel>> getAllStatus() {
        try {
            var status = statusService.getAllStatus();

            return ResponseEntity.ok().body(status);
        } catch (NoStatusException e) {
            logger.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving status", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/status")
    public ResponseEntity<StatusModel> getAllStatus(@RequestBody UUID id) {
        try {
            var status = statusService.getStatus(id);

            return ResponseEntity.ok().body(status);
        } catch (NoStatusException e) {
            logger.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving status", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/status")
    public ResponseEntity<String> getStatus(@RequestBody UUID id) {
        try {
            statusService.deleteById(id);

            return ResponseEntity.ok().body("Status deleted successfully");
        } catch (NoStatusException e) {
            logger.error("Error deleting order");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error deleting order");
        } catch (Exception e) {
            logger.error("Error deleting order", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting order");
        }
    }

//    @PutMapping("/status/{id}")
//    public ResponseEntity<String> updateStatus(@PathVariable("id") UUID id, @RequestBody statusDTO orderDTO) {
//        try {
//            var order = orderMapper.toOrderModel(orderDTO);
//
//            orderService.validateOrder(order);
//            orderService.updateOrder(id, orderDTO);
//
//            logger.info("Order successfully updated");
//
//            return ResponseEntity.status(HttpStatus.OK).body("Order updated successfully!");
//        } catch (InvalidClientException | InvalidAddressException | InvalidProductException e) {
//            logger.error("Error updating order", e);
//
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        } catch (Exception e) {
//            logger.error("Error updating order ", e);
//
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unexpected error: " + e.getMessage());
//        }
//    }
}
