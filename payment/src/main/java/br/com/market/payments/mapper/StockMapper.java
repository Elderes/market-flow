package br.com.market.payments.mapper;

import br.com.market.payments.dto.StockOrderDTO;
import br.com.market.payments.model.StockModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StockMapper {
    public StockModel toStockModel(StockOrderDTO stockOrderDTO) {
        var stock = new StockModel();

        stock.setOrder_id(stockOrderDTO.getOrder_id());
        stock.setClient_id(stockOrderDTO.getClient_id());
        stock.setApproval(stockOrderDTO.isApproval());

        return stock;
    }
}
