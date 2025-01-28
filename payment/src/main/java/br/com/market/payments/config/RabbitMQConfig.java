package br.com.market.payments.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${queue.status.payment}")
    private String queueStatusPayment;

    @Value("${exchange.direct}")
    private String exchangeDirect;

    @Value("${routing.key.status.payment}")
    private String routingKeyStatusPayment;

    // json to object converter configuration
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Payment queue setup for shipping
    @Bean
    public Queue queueStatusPayment() {
        return new Queue(queueStatusPayment, true);
    }

    // Exchange configuration
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeDirect);
    }

    // Bindings configurations
    @Bean
    public Binding bindingQueueSendDirect(DirectExchange exchange) {
        return BindingBuilder.bind(queueStatusPayment()).to(exchange).with(routingKeyStatusPayment);
    }
}