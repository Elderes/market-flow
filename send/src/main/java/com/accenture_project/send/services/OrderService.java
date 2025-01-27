package com.accenture_project.send.services;

import com.accenture_project.send.exceptions.NoOrderException;
import com.accenture_project.send.models.OrderModel;
import com.accenture_project.send.models.ProductModel;
import com.accenture_project.send.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Service class responsible for handling operations related to orders.
 *
 * - saveOrder: Saves a new order to the database, associating it with an address, client, and products.
 * - getOrders: Retrieves all orders from the repository. Throws NoOrderException if no orders are found.
 * - getOrder: Retrieves a specific order by its ID. Throws NoOrderException if the order is not found.
 * - deleteById: Deletes an order by its ID. Throws NoOrderException if the order is not found.
 */

@RequiredArgsConstructor
@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final AddressService addressService;
    private final ClientService clientService;
    private final ProductService productService;

    public void saveOrder(OrderModel order) {
        try {
            logger.info("Saving order with ID: {}", order.getId());

            var address = addressService.verifyAddress(order.getClient().getAddress());
            var client = clientService.verifyClient(order.getClient());
            client.setAddress(address);

            order.setClient(client);

            var products = new ArrayList<ProductModel>();

            for (var product : order.getProducts()) {
                var verifiedProduct = productService.verifyProduct(product);
                verifiedProduct.setOrder(order);
                products.add(verifiedProduct);
            }

            order.setProducts(products);

            orderRepository.save(order);

            logger.info("Order with ID: {} has been saved successfully.", order.getId());
        } catch (Exception e) {
            logger.error("Error occurred while saving order with ID: {}", order.getId(), e);
        }
    }

    public List<OrderModel> getOrders() {
        var orders = orderRepository.findAll();

        if (orders.isEmpty()) {
            throw new NoOrderException("There are no orders");
        }

        return orders;
    }

    public OrderModel getOrder(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoOrderException("Order not found with id:" + id));
    }

    public void deleteById(UUID id) {
        if (getOrder(id) == null) {
            throw new NoOrderException("Order not found with id:" + id);
        }
        orderRepository.deleteById(id);
    }
}