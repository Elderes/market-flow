package com.accenture_project.send.controllers;

import com.accenture_project.send.exceptions.NoAddressException;
import com.accenture_project.send.exceptions.NoClientException;
import com.accenture_project.send.exceptions.NoOrderException;
import com.accenture_project.send.exceptions.NoProductException;
import com.accenture_project.send.models.AddressModel;
import com.accenture_project.send.models.ClientModel;
import com.accenture_project.send.models.OrderModel;
import com.accenture_project.send.models.ProductModel;
import com.accenture_project.send.services.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class SendController {

    private static final Logger logger = LoggerFactory.getLogger(SendController.class);

    private final AddressService addressService;
    private final ClientService clientService;
    private final OrderService orderService;
    private final ProductService productService;
    private final SendService sendService;

    @PostMapping("/send_email/{id}")
    public ResponseEntity<String> sendEmail(@PathVariable("id") UUID id) {
        try {
            var order = orderService.getOrder(id);

            sendService.sendEmail(order);

            return ResponseEntity.ok().body("Email sent");
        } catch (NoOrderException e) {
            logger.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        } catch (Exception e) {
            logger.error("Error while sending email", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while sending email");
        }
    }

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

    @GetMapping("/orders")
    public ResponseEntity<List<OrderModel>> getOrders() {
        try {
            var orders = orderService.getOrders();

            return ResponseEntity.ok().body(orders);
        } catch (NoOrderException e) {
            logger.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving orders", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderModel> getOrderById(@PathVariable("id") UUID id) {
        try {
            var order = orderService.getOrder(id);

            return ResponseEntity.ok().body(order);
        } catch (NoOrderException e) {
            logger.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            logger.error("Error retrieving order", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        try {
            var products = productService.getAllProducts();

            return ResponseEntity.ok().body(products);
        } catch (NoProductException e) {
            logger.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving products", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable("id") UUID id) {
        try {
            var product = productService.getProduct(id);

            return ResponseEntity.ok().body(product);
        } catch (NoProductException e) {
            logger.error(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving product", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
