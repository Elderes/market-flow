package accenture_project.Order.configs;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Value("${exchange.direct}")
    private String exchangeDirect;

    @Value("${routing.key.payment.order}")
    private String routingKeyPaymentOrder;

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {

        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
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
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeDirect);
    }

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
