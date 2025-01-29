package com.accenture_project.order.configs.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class configures RabbitMQ messaging components, including queues,
 * a fanout exchange, and their respective bindings. It ensures that messages
 * are properly routed to the relevant queues using the fanout exchange pattern.
 */

@Configuration
public class RabbitMQConfig {

    @Value("${queue.stock.order}")
    private String queueStockOrder;

    @Value("${queue.payment.order}")
    private String queuePaymentOrder;

    @Value("${exchange.fanout}")
    private String exchangeFanout;

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queueStockOrder() {
        return new Queue(queueStockOrder, true);
    }

    @Bean
    public Queue queuePaymentOrder() {
        return new Queue(queuePaymentOrder, true);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(exchangeFanout);
    }

    @Bean
    public Binding bindingQueueStockOrderFanout(FanoutExchange exchange) {
        return BindingBuilder.bind(queueStockOrder()).to(exchange);
    }

    @Bean
    public Binding bindingQueuePaymentOrderFanout(FanoutExchange exchange) {
        return BindingBuilder.bind(queuePaymentOrder()).to(exchange);
    }
}
