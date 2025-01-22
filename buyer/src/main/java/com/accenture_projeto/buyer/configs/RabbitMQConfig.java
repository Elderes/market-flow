package com.accenture_projeto.buyer.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${broker.queue.request.list.products.name}")
    private String queueName;

    @Value("${broker.exchange.request.list.products.name}")
    private String exchangeName;

    @Value("${routing.key}")
    private String routingKey;

    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding binding(DirectExchange exchange) {
        return new Binding(queueName, Binding.DestinationType.QUEUE, exchange.getName(), routingKey, null);
    }
}
