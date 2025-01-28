package br.com.market.payments.consumer;

import br.com.market.payments.dto.*;
import br.com.market.payments.model.AddressModel;
import br.com.market.payments.model.ClientModel;
import br.com.market.payments.model.OrderModel;
import br.com.market.payments.model.ProductModel;
import br.com.market.payments.service.OrderService;
import br.com.market.payments.service.PayService;
import br.com.market.payments.service.StockService;
import br.com.market.payments.service.PaymentCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class PaymentConsumer {

    private final OrderService orderService;
    private final StockService stockService;
    private final PayService payService;
    private final PaymentCalculationService paymentCalculationService;
    private final RabbitTemplate rabbitTemplate; // Injeção do RabbitTemplate

    @RabbitListener(queues = "${queue.payment.order}")
    public void listenerOrder(OrderDTO orderDTO) {
        OrderModel orderModel = new OrderModel();
        ClientModel client = new ClientModel();

        client.setId(orderDTO.client().id());
        client.setName(orderDTO.client().name());
        client.setCellphone(orderDTO.client().cellphone());
        client.setEmail(orderDTO.client().email());

        // Map nested AddressRecordDTO to AddressModel, if applicable
        if (orderDTO.client().address() != null) {
            AddressModel address = new AddressModel();
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
        orderModel.setProducts(orderService.mapProducts(orderDTO.products()));

        orderService.saveOrder(orderModel);





        // Usando o PaymentCalculationService para calcular o total
        BigDecimal total = paymentCalculationService.calculateTotal(orderDTO.products());

        // Enviando para outra fila
        UUID orderId = orderModel.getId(); // Usando o ID real do pedido
        SendDTO sendDTO = paymentCalculationService.send(total, orderId);

        // Enviar a mensagem para a fila de saída
        rabbitTemplate.convertAndSend("${queue.payment.result}", sendDTO);

        System.out.println("Enviado para fila de saída: " + sendDTO);
    }




    @RabbitListener(queues = "${queue.payment.stock}")
    public void listenStock(StockOrderDTO stockOrderDTO) {
        if (stockOrderDTO.isApproval()) {
            stockService.saveStock(stockOrderDTO);
        }
    }

    @RabbitListener(queues = "${queue.payment.order.pay}")
    public void listenStock(PayDTO payDTO) {
        payService.pay(payDTO);
    }
}
