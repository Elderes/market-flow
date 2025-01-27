package com.accenture_project.order.configs;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    // Queue configuration for payment
    @Bean
    public Queue queuePaymentOrder() {
        return new Queue(queuePaymentOrder, true);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(exchangeFanout);
    }

    // Bindings configurations
    @Bean
    public Binding bindingQueueStockOrderFanout(FanoutExchange exchange) {
        return BindingBuilder.bind(queueStockOrder()).to(exchange);
    }

    @Bean
    public Binding bindingQueuePaymentOrderFanout(FanoutExchange exchange) {
        return BindingBuilder.bind(queuePaymentOrder()).to(exchange);
    }
}
