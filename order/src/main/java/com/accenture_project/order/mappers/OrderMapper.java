package com.accenture_project.order.mappers;

import com.accenture_project.order.dtos.AddressDTO;
import com.accenture_project.order.dtos.ClientDTO;
import com.accenture_project.order.dtos.OrderDTO;
import com.accenture_project.order.dtos.ProductDTO;
import com.accenture_project.order.models.AddressModel;
import com.accenture_project.order.models.ClientModel;
import com.accenture_project.order.models.OrderModel;
import com.accenture_project.order.models.ProductModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class OrderMapper {

    public OrderModel toOrderModel(OrderDTO orderDTO) {
        var order = new OrderModel();
        order.setClient(toClientModel(orderDTO.client()));
        var products = toProductModels(orderDTO.products());
        products.forEach(product -> product.setOrder(order));
        order.setProducts(products);
        order.setOrderDateTime(LocalDateTime.now());
        return order;
    }

    public ClientModel toClientModel(ClientDTO clientDTO) {
        var client = new ClientModel();
        client.setName(clientDTO.name());
        client.setCellphone(clientDTO.cellphone());
        client.setEmail(clientDTO.email());
        client.setAddress(toAddressModel(clientDTO.address()));
        return client;
    }

    public AddressModel toAddressModel(AddressDTO addressDTO) {
        var address = new AddressModel();
        address.setCountry(addressDTO.country());
        address.setState(addressDTO.state());
        address.setCity(addressDTO.city());
        address.setNeighborhood(addressDTO.neighborhood());
        address.setStreet(addressDTO.street());
        address.setNumber(addressDTO.number());
        return address;
    }

    public List<ProductModel> toProductModels(List<ProductDTO> productsDTO) {
        return productsDTO.stream().map(productDTO -> {
            var product = new ProductModel();
            product.setName(productDTO.name());
            product.setQuantity(productDTO.quantity());
            return product;
        }).toList();
    }
}