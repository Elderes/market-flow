package br.summer_academy.stock.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.direct}")
    private String directExchange;

    @Value("${rabbitmq.exchange.fanout}")
    private String fanoutExchange;

    @Value("${rabbitmq.queue.stock.order}")
    private String stockOrderQueue;

    @Value("${rabbitmq.queue.payment.order}")
    private String paymentOrderQueue;

    @Value("${rabbitmq.queue.payment.stock}")
    private String paymentStockQueue;

    @Value("${rabbitmq.queue.order.payment}")
    private String orderPaymentQueue;

    @Value("${rabbitmq.queue.send.payment}")
    private String sendPaymentQueue;

    @Value("${rabbitmq.queue.payment.send}")
    private String paymentSendQueue;

    @Value("${rabbitmq.routing.stock.to.payment}")
    private String routingKeyStockToPayment;

    @Value("${rabbitmq.routing.payment.to.order}")
    private String routingKeyPaymentToOrder;

    @Value("${rabbitmq.routing.payment.to.send}")
    private String routingKeyPaymentToSend;

    @Value("${rabbitmq.routing.send.to.payment}")
    private String routingKeySendToPayment;

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
    public Queue paymentOrderQueue() {
        return new Queue(paymentOrderQueue, true);
    }

    @Bean
    public Queue paymentStockQueue() {
        return new Queue(paymentStockQueue, true);
    }

    @Bean
    public Queue orderPaymentQueue() {
        return new Queue(orderPaymentQueue, true);
    }

    @Bean
    public Queue sendPaymentQueue() {
        return new Queue(sendPaymentQueue, true);
    }

    @Bean
    public Queue paymentSendQueue() {
        return new Queue(paymentSendQueue, true);
    }

    // Bindings (link queues to exchanges with routing keys)
    @Bean
    public Binding bindStockOrderQueue(FanoutExchange orderBroadcastExchange) {
        return BindingBuilder.bind(stockOrderQueue()).to(orderBroadcastExchange);
    }

    @Bean
    public Binding bindPaymentOrderQueue(FanoutExchange orderBroadcastExchange) {
        return BindingBuilder.bind(paymentOrderQueue()).to(orderBroadcastExchange);
    }

    @Bean
    public Binding bindPaymentStockQueue(DirectExchange stockPaymentExchange) {
        return BindingBuilder.bind(paymentStockQueue()).to(stockPaymentExchange).with(routingKeyStockToPayment);
    }

    @Bean
    public Binding bindOrderPaymentQueue(DirectExchange stockPaymentExchange) {
        return BindingBuilder.bind(orderPaymentQueue()).to(stockPaymentExchange).with(routingKeyPaymentToOrder);
    }

    @Bean
    public Binding bindSendPaymentQueue(DirectExchange stockPaymentExchange) {
        return BindingBuilder.bind(sendPaymentQueue()).to(stockPaymentExchange).with(routingKeyPaymentToSend);
    }

    @Bean
    public Binding bindPaymentSendQueue(DirectExchange stockPaymentExchange) {
        return BindingBuilder.bind(paymentSendQueue()).to(stockPaymentExchange).with(routingKeySendToPayment);
    }
}
