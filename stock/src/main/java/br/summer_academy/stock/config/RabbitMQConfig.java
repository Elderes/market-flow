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
    private String directExchange;

    @Value("${rabbitmq.exchange.fanout}")
    private String fanoutExchange;

    @Value("${rabbitmq.queue.stock.order}")
    private String stockOrderQueue;

    @Value("${rabbitmq.queue.payment.stock}")
    private String paymentStockQueue;

    @Value("${rabbitmq.routing.stock.to.payment}")
    private String routingKeyStockToPayment;

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
        return new DirectExchange(directExchange);
    }

    @Bean
    public FanoutExchange orderBroadcastExchange() {
        return new FanoutExchange(fanoutExchange);
    }

    // Define Queues
    @Bean
    public Queue stockOrderQueue() {
        return new Queue(stockOrderQueue, true);
    }

    @Bean
    public Queue paymentStockQueue() {
        return new Queue(paymentStockQueue, true);
    }

    // Bindings (link queues to exchanges with routing keys)
    @Bean
    public Binding bindStockOrderQueue(FanoutExchange orderBroadcastExchange) {
        return BindingBuilder.bind(stockOrderQueue()).to(orderBroadcastExchange);
    }

    @Bean
    public Binding bindPaymentStockQueue(DirectExchange stockPaymentExchange) {
        return BindingBuilder.bind(paymentStockQueue()).to(stockPaymentExchange).with(routingKeyStockToPayment);
    }

}
