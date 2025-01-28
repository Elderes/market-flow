package com.accenture_project.status.mappers;

import com.accenture_project.status.dtos.ProductDTO;
import com.accenture_project.status.exceptions.NoProductException;
import com.accenture_project.status.models.OrderModel;
import com.accenture_project.status.models.ProductModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProductsMapper {

    public List<ProductModel> toProductsModel(List<ProductDTO> productsDTO, OrderModel orderModel) {
        if (productsDTO == null) {
            throw new NoProductException("productsDTO cannot be null");
        }

        return productsDTO.stream().map(productDTO -> {
            var product = new ProductModel();

            product.setId(productDTO.id());
            product.setName(productDTO.name());
            product.setQuantity(productDTO.quantity());
            product.setUnitPrice(productDTO.unitPrice());
            product.setOrder(orderModel);

            return product;
        }).toList();
    }
}
