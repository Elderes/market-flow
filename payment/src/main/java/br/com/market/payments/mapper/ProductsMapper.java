package br.com.market.payments.mapper;

import br.com.market.payments.dto.ProductStockDTO;
import br.com.market.payments.exception.NoProductException;
import br.com.market.payments.model.OrderModel;
import br.com.market.payments.model.ProductModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductsMapper {

    public List<ProductModel> toProductsModel(List<ProductStockDTO> productStockDTOS, OrderModel orderModel) {
        if (productStockDTOS == null) {
            throw new NoProductException("productsDTO cannot be null");
        }

        return productStockDTOS.stream().map(productDTO -> {
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
