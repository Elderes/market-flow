package br.com.market.payments.service;

import br.com.market.payments.dto.PagamentoDto;
import br.com.market.payments.dto.PedidoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PedidoService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public PedidoService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void validarCompra(PedidoDTO pedidoDTO, PagamentoDto pagamentoDto) {
        // Calcular o valor total da compra
        BigDecimal totalCompra = BigDecimal.ZERO; // Inicialize corretamente o BigDecimal com ZERO
        for (PedidoDTO.ProductDTO product : pedidoDTO.getProducts()) {
            totalCompra = totalCompra.add(product.getTotalPrice()); // Use o método add para somar
        }
        pedidoDTO.setTotalCompra(totalCompra); // Atualize o valor total no objeto pedidoDTO


        // Validar se o valor pago é igual ao valor da compra, é feito dessa forma com bigdecimal
        if (pagamentoDto.getValue().compareTo(pedidoDTO.getTotalCompra()) >= 0) {
            // Enviar mensagem de confirmação para outra fila
            enviarMensagemParaFila("queue.send.payment", "Compra validada: " + pedidoDTO.getPedidoId());
            enviarObjetoParaFila("queue.send.payment", pedidoDTO);
        } else {
            // Enviar mensagem de erro para outra fila
            enviarMensagemParaFila("", "Erro na compra: " + pedidoDTO.getPedidoId());
        }
    }

    private void enviarMensagemParaFila(String nomeFila, String mensagem) {
        // Envia a mensagem para a fila especificada
        rabbitTemplate.convertAndSend(nomeFila, mensagem);
        System.out.println("Mensagem enviada para a fila: " + nomeFila);
    }

    private void enviarObjetoParaFila(String nomeFila, PedidoDTO pedidoDTO) {
        // Envia o objeto para a fila especificada
        rabbitTemplate.convertAndSend(nomeFila, pedidoDTO);
        System.out.println("Objeto enviado para a fila: " + nomeFila);
    }
}
