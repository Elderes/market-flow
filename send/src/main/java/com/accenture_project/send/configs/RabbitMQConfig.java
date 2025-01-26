package com.accenture_project.send.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ configuration for asynchronous communication using message queues.
 *
 * - messageConverter: Configures the message converter to JSON using Jackson2JsonMessageConverter.
 * - queueStockOrder: Defines the payment queue that receives order messages.
 * - directExchange: Configures the exchange of type DirectExchange for message routing.
 * - bindingQueueSendPaymentDirect: Binds the queue and exchange using the routing key.
 */

@Configuration
public class RabbitMQConfig {
    @Value("${queue.send.payment}")
    private String queueSendPayment;

    @Value("${exchange.direct}")
    private String exchangeDirect;

    @Value("${routing.key.send.payment}")
    private String routingKeySendPayment;

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queueStockOrder() {
        return new Queue(queueSendPayment, true);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeDirect);
    }

    @Bean
    public Binding bindingQueueSendPaymentDirect(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeySendPayment);
    }
}
