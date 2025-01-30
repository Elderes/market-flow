package br.com.accenture_project.send.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.accenture_project.send.dtos.StatusDTO;
import br.com.accenture_project.send.exceptions.SendNotFoundException;
import br.com.accenture_project.send.models.SendModel;
import br.com.accenture_project.send.repositories.SendRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

@ExtendWith(MockitoExtension.class)
class SendServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private SendRepository sendRepository;

    @InjectMocks
    private SendService sendService;

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
        // Simulando o envio de e-mail sem erros
        doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        sendService.sendEmail(sendModel);

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));  // Verifica se o e-mail foi enviado uma vez
    }

    @Test
    void testSendEmail_Failure() {
        // Simulando falha no envio de e-mail
        doThrow(new MailException("Email error") {}).when(mailSender).send(any(SimpleMailMessage.class));

        // Certificando que a falha não gera exceção inesperada
        assertDoesNotThrow(() -> sendService.sendEmail(sendModel));

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));  // Verifica se o e-mail foi tentado
    }

    @Test
    void testSaveSend() {
        // Simulando comportamento do repositório
        when(sendRepository.save(any(SendModel.class))).thenReturn(sendModel);
        when(sendRepository.findById(sendModel.getId())).thenReturn(Optional.of(sendModel));

        // Testa a operação de salvar sem exceção
        assertDoesNotThrow(() -> sendService.saveSend(statusDTO));

        // Verifica que o método save foi chamado uma vez
        verify(sendRepository, times(1)).save(any(SendModel.class));
    }

    @Test
    void testFindById_Success() {
        // Simulando que o repositório encontra o sendModel
        when(sendRepository.findById(sendModel.getId())).thenReturn(Optional.of(sendModel));

        SendModel result = sendService.findById(sendModel.getId());

        // Verificando se o resultado é o esperado
        assertNotNull(result);
        assertEquals(sendModel.getId(), result.getId());
    }

    @Test
    void testFindById_NotFound() {
        UUID id = UUID.randomUUID();
        // Simulando que o repositório não encontra o sendModel
        when(sendRepository.findById(id)).thenReturn(Optional.empty());

        // Verificando se a exceção é lançada quando o item não é encontrado
        assertThrows(SendNotFoundException.class, () -> sendService.findById(id));
    }

    @Test
    void testGetAllSends() {
        // Simulando que o repositório retorna uma lista com um sendModel
        when(sendRepository.findAll()).thenReturn(List.of(sendModel));

        List<SendModel> result = sendService.getAllSends();

        // Verificando que a lista não está vazia
        assertFalse(result.isEmpty());

        // Verifica se o método findAll foi chamado uma vez
        verify(sendRepository, times(1)).findAll();
    }
}
