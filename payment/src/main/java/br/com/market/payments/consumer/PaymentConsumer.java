package br.com.market.payments.consumer;

import br.com.market.payments.dto.*;
import br.com.market.payments.mapper.PaymentMapper;
import br.com.market.payments.model.*;
import br.com.market.payments.service.OrderService;
import br.com.market.payments.service.StockService;
import br.com.market.payments.service.PaymentCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class PaymentConsumer {

    private final OrderService orderService;
    private final StockService stockService;
    private final PayService payService;
    private final PaymentCalculationService paymentCalculationService;
    private final RabbitTemplate rabbitTemplate; // Injeção do RabbitTemplate
    private final PaymentMapper paymentMapper;

    @RabbitListener(queues = "${queue.payment.order}")
    public void listenerOrder(OrderDTO orderDTO) {

//        OrderModel orderModel = new OrderModel();
//        ClientModel client = new ClientModel();

        // Logando o DTO recebido
        System.out.println("Recebendo pedido: " + orderDTO);

        // Validação do DTO
        if (orderDTO == null || orderDTO.client() == null || orderDTO.products() == null || orderDTO.products().isEmpty()) {
            System.err.println("Pedido inválido ou incompleto: " + orderDTO);
            return;
        }

        OrderModel orderModel = null;
        try {
            orderModel = new OrderModel();
            ClientModel client = new ClientModel();

            client.setId(orderDTO.client().id());
            client.setName(orderDTO.client().name());
            client.setCellphone(orderDTO.client().cellphone());
            client.setEmail(orderDTO.client().email());

            if (orderDTO.client().address() != null) {
                AddressModel address = new AddressModel();
                address.setId(orderDTO.client().address().id());
                address.setCountry(orderDTO.client().address().country());
                address.setState(orderDTO.client().address().state());
                address.setCity(orderDTO.client().address().city());
                address.setStreet(orderDTO.client().address().street());
                address.setNeighborhood(orderDTO.client().address().neighborhood());
                address.setNumber(orderDTO.client().address().number());
                client.setAddress(address);
            }

            orderModel.setId(orderDTO.id());
            orderModel.setClient(client);
            orderModel.setOrderDateTime(orderDTO.orderDateTime());

            // Logando antes de salvar no banco
            System.out.println("Salvando pedido: " + orderModel);

            orderService.saveOrder(orderModel);

            BigDecimal total = paymentCalculationService.calculateTotal(orderDTO.products());
            UUID orderId = orderModel.getId();
            SendDTO sendDTO = paymentCalculationService.send(total, orderId);

            // Enviando para a fila de status
            rabbitTemplate.convertAndSend("${queue.status.payment}", sendDTO);
            System.out.println("Enviado para fila de status: " + sendDTO);

        } catch (Exception e) {
            System.err.println("Erro ao processar pedido: " + e.getMessage());
            e.printStackTrace();
        }


        // Usando o PaymentCalculationService para calcular o total
        BigDecimal total = paymentCalculationService.calculateTotal(orderDTO.products());

        // Enviando para outra fila
        UUID orderId = orderModel.getId(); // Usando o ID real do pedido
        paymentDTO sendDTO = paymentCalculationService.send(total, orderId);

        // Enviar a mensagem para a fila de saída
        rabbitTemplate.convertAndSend("${queue.status.payment}", DTO);

        System.out.println("Enviado para fila de status: " + sendDTO);
    }




    @RabbitListener(queues = "${queue.payment.stock}")
    public void listenStock(StockOrderDTO stockOrderDTO) {
        if (stockOrderDTO.isApproval()) {
            stockService.saveStock(stockOrderDTO);
            var order = stockService.findOrder(stockOrderDTO.getOrder_id());
            var payment = new PaymentModel();
            payment.setTotalPrice(paymentCalculationService.calculateTotal());
            var paymentDTO = paymentMapper.toPaymentDTOMapper();
            System.out.println("consumido de stock abraon");
        }
    }


}
