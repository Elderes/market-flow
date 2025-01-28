package com.accenture_project.order.mappers;

import com.accenture_project.order.dtos.ProductProducerDTO;
import com.accenture_project.order.models.ProductModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductsProducerMapper {

    public List<ProductProducerDTO> toProductsProducerDTO(List<ProductModel> productsModel) {
        var listProductProducerDTO = new ArrayList<ProductProducerDTO>();

        for (var productModel : productsModel) {
            listProductProducerDTO.add(new ProductProducerDTO(productModel.getName(), productModel.getQuantity(), productModel.getUnitPrice()));
        }

        return listProductProducerDTO;
    }
}
