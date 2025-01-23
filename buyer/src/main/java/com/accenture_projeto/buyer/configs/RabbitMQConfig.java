package com.accenture_projeto.buyer.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${request.products.queue}")
    private String queue1;

    @Value("${products.list.queue}")
    private String queue2;

    @Value("${send.order.queue}")
    private String queue3;

    @Value("${exchange-direct}")
    private String exchangeName;

    @Value("${routing.key.request.products}")
    private String routingKeyQueue1;

    @Value("${routing.key.products.list}")
    private String routingKeyQueue2;

    @Value("${routing.key.send.order}")
    private String routingKeyQueue3;

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public Queue queue1() {
        return new Queue(queue1, true);
    }

    @Bean
    public Queue queue2() {
        return new Queue(queue2, true);
    }

    @Bean
    public Queue queue3() {
        return new Queue(queue3, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding binding1(@Qualifier("queue1") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeyQueue1);
    }

    @Bean
    public Binding binding2(@Qualifier("queue2") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeyQueue2);
    }

    @Bean
    public Binding binding3(@Qualifier("queue3") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeyQueue3);
    }
}
