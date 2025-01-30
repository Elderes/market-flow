package br.summer_academy.stock.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * OpenAPIConfig Class
 *
 * This class configures Swagger documentation for the "Pedido API" microservice.
 * It defines API metadata such as title, version, description, and contact information.
 * The customOpenAPI() method creates a bean that integrates this configuration into the Spring Boot application.
 *
 */

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("Abraão Borges");
        contact.setEmail("abraao.borgespimenta@gmail.com");

        return new OpenAPI().info(new Info().title("Stock API").version("1.0").description("Stock microservice.").contact(contact));
    }
}