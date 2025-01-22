package br.com.hopefood.pagamentos.ampq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
@Configuration
public class PagamentoAMPQVonfig {
    @Bean
    public Queue criaFila(){
        return new Queue("pagamento.concluido", false);
    }

    @Bean
    public RabbitAdmin criaRabbitAdmin(ConnectionFactory conn){
        return new RabbitAdmin(conn);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializaAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }
}
