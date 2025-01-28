package br.com.market.payments.service;

import br.com.market.payments.dto.PagamentoDto;
import br.com.market.payments.dto.PedidoDTO;
import br.com.market.payments.dto.ProductDTO;
import br.com.market.payments.model.OrderModel;
import br.com.market.payments.model.ProductModel;
import br.com.market.payments.repository.OrderRepository;
import br.com.market.payments.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final RabbitTemplate rabbitTemplate;
    private final OrderRepository orderRepository;

    public void saveOrder(OrderModel order) {
        System.out.println(order.getId());
        orderRepository.save(order);
    }

    public OrderModel getOrderById(UUID id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void validarCompra(PagamentoDto pagamentoDto) {


    }

    private void enviarObjetoParaFila(String nomeFila, PedidoDTO pedidoDTO) {
        // Envia o objeto para a fila especificada

        rabbitTemplate.convertAndSend("exchange.direct", "routing.key.send.payment", "teste"); //pedido
        System.out.println("Objeto enviado para a fila: " + nomeFila);
    }

    public List<ProductModel> mapProducts(List<ProductDTO> productDTOs) {
        return productDTOs.stream().map(dto -> {
            ProductModel productModel = new ProductModel();
            productModel.setId(dto.id());
            productModel.setName(dto.name());
            productModel.setQuantity(dto.quantity());
            productModel.setPrice(dto.price());
            return productModel;
        }).collect(Collectors.toList());
    }


}
