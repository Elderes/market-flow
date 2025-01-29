package com.accenture_project.send.services;

import com.accenture_project.send.dtos.StatusDTO;
import com.accenture_project.send.exceptions.SendNotFoundException;
import com.accenture_project.send.models.SendModel;
import com.accenture_project.send.repositories.SendRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/*
 * SendService Class
 *
 * Handles the business logic related to sending order confirmation emails and saving the send status.
 * Interacts with the SendRepository to persist send data and uses JavaMailSender to send emails to customers.
 * Contains methods to save send status, retrieve send details, and send emails.
 */


@RequiredArgsConstructor
@Service
public class SendService {

    private static final Logger logger = LoggerFactory.getLogger(SendService.class);

    private final JavaMailSender mailSender;

    private final SendRepository sendRepository;

    @Value("{$spring.mail.username}")
    private String emailFrom;

    public void sendEmail(SendModel sendModel) {
        try {
            logger.info("Sending email ");

            var message = new SimpleMailMessage();
            message.setTo(sendModel.getEmail());
            message.setFrom(emailFrom);
            message.setSubject("Pedido concluído");
            message.setText("Obrigado por comprar conosco! Estamos preparando o envio.");

            mailSender.send(message);
        } catch (MailException e) {
            logger.error("Error sending email to customer: {}", e.getMessage());
        }
    }

    public void saveSend(StatusDTO statusDTO) {
        var sendModel = new SendModel();

        sendModel.setHasSend(true);
        sendModel.setOrderId(statusDTO.orderId());
        sendModel.setEmail(statusDTO.email());

        var send = sendRepository.save(sendModel);

        var sendId = send.getId();

        sendEmail(findById(sendId));
    }

    public SendModel findById(UUID id) {
        return sendRepository.findById(id).orElseThrow(() -> new SendNotFoundException("Send not found"));
    }

    public List<SendModel> getAllSends() {
        return sendRepository.findAll();
    }
}