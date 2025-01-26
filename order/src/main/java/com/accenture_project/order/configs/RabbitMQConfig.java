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

    @Value("${queue.payment.order.pay}")
    private String queuePaymentOrderPay;

    @Value("${exchange.fanout}")
    private String exchangeFanout;

    @Value("${exchange.direct}")
    private String exchangeDirect;

    @Value("${routing.key.payment.order}")
    private String routingKeyPaymentOrderPay;

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

    // Queue configuration for payment
    @Bean
    public Queue queuePaymentOrderPay() {
        return new Queue(queuePaymentOrderPay, true);
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
    public Binding bindingQueueStockOrderFanout(FanoutExchange exchange) {
        return BindingBuilder.bind(queueStockOrder()).to(exchange);
    }

    @Bean
    public Binding bindingQueuePaymentOrderFanout(FanoutExchange exchange) {
        return BindingBuilder.bind(queuePaymentOrder()).to(exchange);
    }

    @Bean
    public Binding bindingQueuePaymentDirect(DirectExchange exchange) {
        return BindingBuilder.bind(queuePaymentOrderPay()).to(exchange).with(routingKeyPaymentOrderPay);
    }
}
