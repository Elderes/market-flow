package com.accenture_project.order.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class RabbitMQConfig {

    @Value("${queue.stock.order}")
    private String queueStockOrder;

    @Value("${queue.payment.order}")
    private String queuePaymentOrder;

    @Value("${exchange.fanout}")
    private String exchangeFanout;

    @Value("${exchange.direct}")
    private String exchangeDirect;

    @Value("${routing.key.payment.order}")
    private String routingKeyPaymentOrder;

    // json to object converter configuration
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Queue setup for stock and payment
    @Bean
    public Queue queueStockOrder() {
        return new Queue(queueStockOrder, true);
    }

    // Queue configuration for payment
    @Bean
    public Queue queuePaymentOrder() {
        return new Queue(queuePaymentOrder, true);
    }

    // Exchange configuration
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(exchangeFanout);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeDirect);
    }

    // Bindings configurations
    @Bean
    public Binding bindingQueueStockOrderFanout(@Qualifier("queueStockOrder") Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding bindingQueuePaymentOrderFanout(@Qualifier("queuePaymentOrder") Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding bindingQueuePaymentDirect(@Qualifier("queuePaymentOrder") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeyPaymentOrder);
    }
}
