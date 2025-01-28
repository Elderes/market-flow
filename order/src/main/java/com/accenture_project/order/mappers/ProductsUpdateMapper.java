package com.accenture_project.order.mappers;

import com.accenture_project.order.dtos.OrderDTO;
import com.accenture_project.order.dtos.ProductDTO;
import com.accenture_project.order.models.ProductModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductsUpdateMapper {

    public List<ProductModel> toProductsModel(List<ProductModel> products, OrderDTO orderDTO) {
        for (int i = 0; i < products.size(); i++) {
            products.get(i).setName(orderDTO.products().get(i).name());
            products.get(i).setQuantity(orderDTO.products().get(i).quantity());
            products.get(i).setUnitPrice(orderDTO.products().get(i).unitPrice());
        }

        return products;
    }

    public ProductModel toProductModel(ProductModel product, ProductDTO productDTO) {
        product.setName(productDTO.name());
        product.setQuantity(productDTO.quantity());
        product.setUnitPrice(productDTO.unitPrice());

        return product;
    }
}
