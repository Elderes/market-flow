package com.accenture_project.status.mappers;

import com.accenture_project.status.dtos.ProductDTO;
import com.accenture_project.status.models.ProductModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductsMapper {

    public List<ProductModel> toProductsModel(List<ProductDTO> productsDTO) {
        return productsDTO.stream().map(productDTO -> {
            var product = new ProductModel();

            product.setName(productDTO.name());
            product.setQuantity(productDTO.quantity());
            product.setUnitPrice(productDTO.unitPrice());

            return product;
        }).toList();
    }
}
