package br.com.market.payments.service;

import br.com.market.payments.dto.StockOrderDTO;
import br.com.market.payments.mapper.StockMapper;
import br.com.market.payments.model.OrderModel;
import br.com.market.payments.model.StockModel;
import br.com.market.payments.repository.OrderRepository;
import br.com.market.payments.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;
    private final OrderRepository orderRepository;

    public void saveStock(StockOrderDTO stockOrderDTO) {
        var stock = stockMapper.toStockModel(stockOrderDTO);
        stockRepository.save(stock);
    }

    public StockModel getStockOrder(Long id) {
        return stockRepository.findById(id).orElseThrow(() -> new RuntimeException("nao existe"));

    }

    public OrderModel findOrder(UUID id){
        var order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return order;
    }
}
