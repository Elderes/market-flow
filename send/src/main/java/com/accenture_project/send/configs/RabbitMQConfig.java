package com.accenture_project.send.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${queue.send.payment}")
    private String queueSendPayment;

    @Value("${exchange.direct}")
    private String exchangeDirect;

    @Value("${routing.key.send.payment}")
    private String routingKeySendPayment;

    // json to object converter configuration
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Payment queue setup for shipping
    @Bean
    public Queue queueStockOrder() {
        return new Queue(queueSendPayment, true);
    }

    // Exchange configuration
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeDirect);
    }

    // Bindings configurations
    @Bean
    public Binding bindingQueueSendDirect(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeySendPayment);
    }
}
