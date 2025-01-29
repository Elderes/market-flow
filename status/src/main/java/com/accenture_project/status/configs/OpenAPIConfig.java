package com.accenture_project.status.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures OpenAPI documentation for the "Pedido API".
 * This class sets up API metadata, including title, version, and contact information.
 */

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("Lucas Matheus Gomes de Lima");
        contact.setEmail("lucazmatehus14@gmail.com");

        return new OpenAPI().info(new Info()
                .title("Pedido API")
                .version("1.0")
                .description("his API allows managing the status of orders. It includes endpoints to retrieve, create, update, and delete order statuses. The available operations are:\n" +
                        "\n" +
                        "GET /all_status: Retrieves all order statuses.\n" +
                        "GET /status/{id}: Retrieves the status of a specific order by its ID.\n" +
                        "DELETE /status/{id}: Deletes the status of a specific order by its ID.\n" +
                        "Order statuses contain information such as payment status, last update time, client email, and total price.")
                .contact(contact));
    }
}