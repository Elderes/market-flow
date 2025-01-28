package br.summer_academy.stock.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.direct}")
    private String exchange_direct;

    @Value("${rabbitmq.queue.stock.order}")
    private String queue_stock_order;

    @Value("${rabbitmq.queue.payment.stock}")
    private String queue_payment_stock;

    @Value("${rabbitmq.queue.status.stock}")
    private String queue_status_stock;

    @Value("${rabbitmq.routing.stock.to.payment}")
    private String key_stock_to_payment;
    
    @Value("${rabbitmq.routing.stock.to.status}")
    private String key_stock_to_status;

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        org.springframework.amqp.rabbit.core.RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    // Define Exchanges
    @Bean
    public DirectExchange stockPaymentExchange() {
        return new DirectExchange(exchange_direct);
    }

    @Bean
    public DirectExchange stockStatusExchange() {
        return new DirectExchange(exchange_direct);
    }

    // Define Queues
    @Bean
    public Queue stockOrderQueue() {
        return new Queue(queue_stock_order, true);
    }

    @Bean
    public Queue paymentStockQueue() {
        return new Queue(queue_payment_stock, true);
    }
    
    @Bean
    public Queue statusStockQueue() {
        return new Queue(queue_status_stock, true);
    }

    // Bindings (link queues to exchanges with routing keys)
    @Bean
    public Binding bindPaymentStockQueue(DirectExchange stockPaymentExchange) {
        return BindingBuilder.bind(paymentStockQueue()).to(stockPaymentExchange).with(key_stock_to_payment);
    
    }
    @Bean
    public Binding bindStatusStockQueue(DirectExchange stockStatusExchange) {
        return BindingBuilder.bind(statusStockQueue()).to(stockStatusExchange).with(key_stock_to_status);
    }

}
