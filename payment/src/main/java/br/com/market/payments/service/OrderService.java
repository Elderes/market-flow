package br.com.market.payments.service;

import br.com.market.payments.dto.PagamentoDto;
import br.com.market.payments.dto.PedidoDTO;
import br.com.market.payments.model.OrderModel;
import br.com.market.payments.repository.OrderRepository;
import br.com.market.payments.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
//        System.out.println("Serviço rodando");
//
//
//        if (!pedidos.isEmpty()) {
//           PedidoDTO pedidoDTO = pedidos.get(pedidos.size() - 1);
//
//        if(true){
//            var pedido = pedidoRepository.findAllById()
//
//            BigDecimal totalCompra = BigDecimal.ZERO; // Inicialize corretamente o BigDecimal com ZERO
//            for (PedidoDTO.ProductDTO product : pedidoDTO.getProducts()) {
//                totalCompra = totalCompra.add(product.getTotalPrice()); // Use o método add para somar
//            }
//            pedidoDTO.setTotalCompra(totalCompra);
//
//            if (true) {
//                if (pagamentoDto.getValue().compareTo(pedidoDTO.getTotalCompra()) >= 0) {
//                enviarMensagemParaFila("queue.send.payment", "Compra validada: " + pedidoDTO.getPedidoId());
//                enviarObjetoParaFila("queue.send.payment", pedidoDTO);
//            }
//
//            pedidos.remove(pedidos.size() - 1);
//        }
    }

    //    private void enviarMensagemParaFila(String nomeFila, String mensagem) {
//        // Envia a mensagem para a fila especificada
//        rabbitTemplate.convertAndSend(nomeFila, mensagem);
//        System.out.println("Mensagem enviada para a fila: " + nomeFila);
//    }
//
    private void enviarObjetoParaFila(String nomeFila, PedidoDTO pedidoDTO) {
        // Envia o objeto para a fila especificada

        rabbitTemplate.convertAndSend("exchange.direct", "routing.key.send.payment", "teste"); //pedido
        System.out.println("Objeto enviado para a fila: " + nomeFila);
    }
}
