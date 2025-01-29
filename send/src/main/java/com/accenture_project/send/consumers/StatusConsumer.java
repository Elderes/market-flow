package com.accenture_project.send.consumers;

import com.accenture_project.send.dtos.StatusDTO;
import com.accenture_project.send.services.SendService;
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
    
    private final SendService sendService;

    @RabbitListener(queues = "${queue.send.status}")
    public void consumerMessage(StatusDTO statusDTO) {
        try {
            sendService.saveSend(statusDTO);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
