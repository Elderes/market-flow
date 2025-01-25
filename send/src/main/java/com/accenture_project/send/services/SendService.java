package com.accenture_project.send.services;

import com.accenture_project.send.models.OrderModel;
import com.accenture_project.send.models.ProductModel;
import com.accenture_project.send.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SendService {

    private final OrderRepository orderRepository;
    private final AddressService addressService;
    private final ClientService clientService;
    private final ProductService productService;

    public void saveOrder(OrderModel order) {
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
    }

    public List<OrderModel> getOrders() {
        return orderRepository.findAll();
    }

    public void sendEmail(OrderModel order) {

    }
}
