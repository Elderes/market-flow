package com.accenture_project.order.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        Contact contact = new Contact();
        contact.name("Lucas Matheus Gomes de Lima");
        contact.setEmail("lucazmatehus14@gmail.com");

        return new OpenAPI()
                .info(new Info()
                        .title("Comprador API")
                        .version("1.0")
                        .description("")
                        .contact(contact));
    }
}