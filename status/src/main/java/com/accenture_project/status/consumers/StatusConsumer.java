package com.accenture_project.status.consumers;

import com.accenture_project.status.dtos.OrderRecordDTO;
import com.accenture_project.status.dtos.PaymentRecordDTO;
import com.accenture_project.status.models.AddressModel;
import com.accenture_project.status.models.ClientModel;
import com.accenture_project.status.models.OrderModel;
import com.accenture_project.status.models.PaymentModel;
import com.accenture_project.status.services.StatusService;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ message consumer responsible for processing orders and sending email notifications.
 *
 * - consumerMessage: Receives a message with order details, saves it to the database, and sends a confirmation email.
 *                    If any error occurs during processing, it is logged.
 */

@RequiredArgsConstructor
@Component
public class StatusConsumer {

    private static final Logger logger = LoggerFactory.getLogger(StatusConsumer.class);

    private final StatusService statusService;

    @RabbitListener(queues = "${queue.status.stock}")
    public void consumerStockMessage(OrderRecordDTO orderDto) {
        OrderModel order = new OrderModel();
        // Map ClientRecordDTO to ClientModel
        ClientModel client = new ClientModel();
        client.setId(orderDto.client().id());
        client.setName(orderDto.client().name());
        client.setCellphone(orderDto.client().cellphone());
        client.setEmail(orderDto.client().email());
        
        order.setId(orderDto.id());
        order.setClient(client);
        
        // Map nested AddressRecordDTO to AddressModel, if applicable
        if (orderDto.client().address() != null) {
            AddressModel address = new AddressModel();
            address.setCountry(orderDto.client().address().country());
            address.setState(orderDto.client().address().state());
            address.setCity(orderDto.client().address().city());
            address.setStreet(orderDto.client().address().street());
            address.setNeighborhood(orderDto.client().address().neighborhood());
            address.setNumber(orderDto.client().address().number());;

            client.setAddress(address);
        }
        
        try {
            statusService.saveOrder(order);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @RabbitListener(queues = "${queue.status.payment}")
    public void consumerPaymentMessage(PaymentRecordDTO paymentDto) {
        PaymentModel payment = new PaymentModel();
        payment.setId(paymentDto.id());
        payment.setCode(paymentDto.code());
        payment.setDatetime(paymentDto.dateTime());
        payment.setValue(paymentDto.value());
        try {
            statusService.paymentOrder(payment);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
