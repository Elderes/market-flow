package br.com.market.payments.mapper;

import br.com.market.payments.dto.StockOrderDTO;
import br.com.market.payments.model.OrderModel;
import br.com.market.payments.model.StockModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StockOrderMapper {

    private final ProductsMapper productsMapper;

    public StockModel toStockModel(StockOrderDTO stockOrderDTO, OrderModel orderModel) {
        var stockModel = new StockModel();

        stockModel.setOrderId(stockOrderDTO.orderId());
        stockModel.setProducts(productsMapper.toProductsModel(stockOrderDTO.products(), orderModel));
        stockModel.setOrderDateTime(stockOrderDTO.orderDateTime());

        return stockModel;
    }
}
