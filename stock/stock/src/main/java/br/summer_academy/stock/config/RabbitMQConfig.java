package br.summer_academy.stock.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Exchange Definitions
    private static final String EXCHANGE_ORDER_BROADCAST = "exchange.order.broadcast";
    private static final String EXCHANGE_STOCK_PAYMENT = "exchange.stock.payment";
    private static final String EXCHANGE_PAYMENT_SEND = "exchange.payment.send";
    private static final String EXCHANGE_SEND_CONFIRMATION = "exchange.send.confirmation";

    // Queue Definitions
    private static final String QUEUE_STOCK_ORDER = "queue.stock.order";
    private static final String QUEUE_PAYMENT_ORDER = "queue.payment.order";
    private static final String QUEUE_PAYMENT_STOCK = "queue.payment.stock";
    private static final String QUEUE_ORDER_PAYMENT = "queue.order.payment";
    private static final String QUEUE_SEND_PAYMENT = "queue.send.payment";
    private static final String QUEUE_PAYMENT_SEND = "queue.payment.send";

    // Routing Keys
    private static final String ROUTING_KEY_ORDER_BROADCAST = "order.broadcast";
    private static final String ROUTING_KEY_STOCK_TO_PAYMENT = "stock.to.payment";
    private static final String ROUTING_KEY_PAYMENT_TO_ORDER = "payment.to.order";
    private static final String ROUTING_KEY_PAYMENT_TO_SEND = "payment.to.send";
    private static final String ROUTING_KEY_SEND_TO_PAYMENT = "send.to.payment";

    // Define Exchanges
    @Bean
    public FanoutExchange orderBroadcastExchange() {
        return new FanoutExchange(EXCHANGE_ORDER_BROADCAST);
    }

    @Bean
    public DirectExchange stockPaymentExchange() {
        return new DirectExchange(EXCHANGE_STOCK_PAYMENT);
    }

    @Bean
    public DirectExchange paymentSendExchange() {
        return new DirectExchange(EXCHANGE_PAYMENT_SEND);
    }

    @Bean
    public DirectExchange sendConfirmationExchange() {
        return new DirectExchange(EXCHANGE_SEND_CONFIRMATION);
    }

    // Define Queues
    @Bean
    public Queue stockOrderQueue() {
        return new Queue(QUEUE_STOCK_ORDER, true);
    }

    @Bean
    public Queue paymentOrderQueue() {
        return new Queue(QUEUE_PAYMENT_ORDER, true);
    }

    @Bean
    public Queue paymentStockQueue() {
        return new Queue(QUEUE_PAYMENT_STOCK, true);
    }

    @Bean
    public Queue orderPaymentQueue() {
        return new Queue(QUEUE_ORDER_PAYMENT, true);
    }

    @Bean
    public Queue sendPaymentQueue() {
        return new Queue(QUEUE_SEND_PAYMENT, true);
    }

    @Bean
    public Queue paymentSendQueue() {
        return new Queue(QUEUE_PAYMENT_SEND, true);
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
        return BindingBuilder.bind(paymentStockQueue()).to(stockPaymentExchange).with(ROUTING_KEY_STOCK_TO_PAYMENT);
    }

    @Bean
    public Binding bindOrderPaymentQueue(DirectExchange stockPaymentExchange) {
        return BindingBuilder.bind(orderPaymentQueue()).to(stockPaymentExchange).with(ROUTING_KEY_PAYMENT_TO_ORDER);
    }

    @Bean
    public Binding bindSendPaymentQueue(DirectExchange paymentSendExchange) {
        return BindingBuilder.bind(sendPaymentQueue()).to(paymentSendExchange).with(ROUTING_KEY_PAYMENT_TO_SEND);
    }

    @Bean
    public Binding bindPaymentSendQueue(DirectExchange sendConfirmationExchange) {
        return BindingBuilder.bind(paymentSendQueue()).to(sendConfirmationExchange).with(ROUTING_KEY_SEND_TO_PAYMENT);
    }
}
