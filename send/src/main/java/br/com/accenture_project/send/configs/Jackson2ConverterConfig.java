package br.com.accenture_project.send.configs;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Jackson2ConverterConfig Class
 *
 * Configures a Jackson JSON message converter for RabbitMQ integration.
 * Provides a bean for converting messages to and from JSON format.
 */

@Configuration
public class Jackson2ConverterConfig {

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
