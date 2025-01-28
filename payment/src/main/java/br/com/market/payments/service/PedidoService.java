package br.com.market.payments.service;

import br.com.market.payments.dto.PagamentoDto;
import br.com.market.payments.dto.PedidoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final RabbitTemplate rabbitTemplate;

    private final List<PedidoDTO> pedidos;


    public PedidoService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.pedidos = new ArrayList<>();
    }

    public void adicionaPedido(PedidoDTO pedido) {
        pedidos.add(pedido);
    }

    public void validarCompra(PagamentoDto pagamentoDto) {
        System.out.println("Serviço rodando");


        if(true){
            PedidoDTO pedidoDTO = pedidos.get(pedidos.size() - 1);


            if (true) {

                enviarObjetoParaFila("queue.send.payment", pedidoDTO);
            }

            pedidos.remove(pedidos.size() - 1);
        }
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
