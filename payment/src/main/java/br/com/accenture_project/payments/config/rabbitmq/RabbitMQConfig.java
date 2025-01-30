package br.com.accenture_project.payments.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Configures RabbitMQ for message queueing and routing.
 * - Defines the queue for payment status.
 * - Configures the message converter to handle JSON messages.
 * - Sets up a direct exchange for routing messages.
 * - Binds the queue to the exchange with a specified routing key.
 */

@Configuration
public class RabbitMQConfig {
    @Value("${queue.status.payment}")
    private String queueStatusPayment;

    @Value("${exchange.direct}")
    private String exchangeDirect;

    @Value("${routing.key.status.payment}")
    private String routingKeyStatusPayment;

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queueStatusPayment() {
        return new Queue(queueStatusPayment, true);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeDirect);
    }

    @Bean
    public Binding bindingQueueSendDirect(DirectExchange exchange) {
        return BindingBuilder.bind(queueStatusPayment()).to(exchange).with(routingKeyStatusPayment);
    }
}