package com.accenture_project.send.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.accenture_project.send.dtos.StatusDTO;
import com.accenture_project.send.exceptions.SendNotFoundException;
import com.accenture_project.send.models.SendModel;
import com.accenture_project.send.repositories.SendRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SendServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private SendRepository sendRepository;

    @InjectMocks
    private SendService sendService;

    @Value("${spring.mail.username}")
    private String emailFrom;

    private SendModel sendModel;
    private StatusDTO statusDTO;

    @BeforeEach
    void setUp() {
        sendModel = new SendModel();
        sendModel.setId(UUID.randomUUID());
        sendModel.setEmail("test@example.com");
        sendModel.setOrderId(UUID.randomUUID());
        sendModel.setHasSend(true);

        statusDTO = new StatusDTO(sendModel.getEmail(), sendModel.getOrderId());
    }

    @Test
    void testSendEmail_Success() {
        doNothing().when(mailSender).send(any(SimpleMailMessage.class));
        sendService.sendEmail(sendModel);
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void testSendEmail_Failure() {
        doThrow(new MailException("Email error") {}).when(mailSender).send(any(SimpleMailMessage.class));
        assertDoesNotThrow(() -> sendService.sendEmail(sendModel));
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void testSaveSend() {
        when(sendRepository.save(any(SendModel.class))).thenReturn(sendModel);
        when(sendRepository.findById(sendModel.getId())).thenReturn(Optional.of(sendModel));

        assertDoesNotThrow(() -> sendService.saveSend(statusDTO));
        verify(sendRepository, times(1)).save(any(SendModel.class));
    }

    @Test
    void testFindById_Success() {
        when(sendRepository.findById(sendModel.getId())).thenReturn(Optional.of(sendModel));
        SendModel result = sendService.findById(sendModel.getId());
        assertNotNull(result);
        assertEquals(sendModel.getId(), result.getId());
    }

    @Test
    void testFindById_NotFound() {
        UUID id = UUID.randomUUID();
        when(sendRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(SendNotFoundException.class, () -> sendService.findById(id));
    }

    @Test
    void testGetAllSends() {
        when(sendRepository.findAll()).thenReturn(List.of(sendModel));
        assertFalse(sendService.getAllSends().isEmpty());
        verify(sendRepository, times(1)).findAll();
    }
}
