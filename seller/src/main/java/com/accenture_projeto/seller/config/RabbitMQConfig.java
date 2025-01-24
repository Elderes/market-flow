package com.accenture_projeto.seller.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue requestProductsQueue() {
        return new Queue("request-products-queue", true);
    }

    @Bean
    public Queue productsListQueue() {
        return new Queue("products-list-queue", true);
    }

    @Bean
    public Queue endOrderQueue() {
        return new Queue("end-order-queue", true);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("my-exchange-direct");
    }

    // Bind queues to the exchange with the routing keys
    @Bean
    public Binding bindingRequestProducts(Queue requestProductsQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(requestProductsQueue)
                             .to(directExchange)
                             .with("key-1");
    }

    @Bean
    public Binding bindingProductsList(Queue productsListQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(productsListQueue)
                             .to(directExchange)
                             .with("key-2");
    }

    @Bean
    public Binding bindingEndOrder(Queue endOrderQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(endOrderQueue)
                             .to(directExchange)
                             .with("key-03");
    }
}
