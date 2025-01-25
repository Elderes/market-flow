package accenture_project.Order.mappers;

import accenture_project.Order.dtos.AddressDTO;
import accenture_project.Order.dtos.ClientDTO;
import accenture_project.Order.dtos.OrderDTO;
import accenture_project.Order.dtos.ProductDTO;
import accenture_project.Order.models.AddressModel;
import accenture_project.Order.models.ClientModel;
import accenture_project.Order.models.OrderModel;
import accenture_project.Order.models.ProductModel;
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