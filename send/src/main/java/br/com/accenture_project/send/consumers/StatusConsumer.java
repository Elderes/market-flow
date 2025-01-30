package br.com.accenture_project.send.consumers;

import br.com.accenture_project.send.dtos.StatusDTO;
import br.com.accenture_project.send.services.SendService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*
 * StatusConsumer Class
 *
 * Listens for messages on the "send.status" RabbitMQ queue.
 * Processes the received StatusDTO message and saves the status using SendService.
 * Handles exceptions by logging errors.
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
