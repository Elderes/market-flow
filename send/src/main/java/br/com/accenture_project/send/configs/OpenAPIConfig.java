package br.com.accenture_project.send.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * OpenAPIConfig Class
 *
 * Configures OpenAPI documentation for the project.
 * Sets up API metadata including title, version, and contact information.
 */

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("Lucas Matheus Gomes de Lima");
        contact.setEmail("lucazmatehus14@gmail.com");

        return new OpenAPI()
                .info(new Info()
                        .title("Envio API")
                        .version("1.0")
                        .description("The Send API provides endpoints for handling the status of shipments related to customer orders.\n" +
                                "\n" +
                                "POST /send/status: This endpoint receives shipment status updates for an order and sends an email notification to the customer confirming the shipment status.\n" +
                                "\n" +
                                "GET /sends: Retrieves a list of all shipment statuses stored in the system.\n" +
                                "\n" +
                                "GET /send/{id}: Retrieves the details of a specific shipment status using the unique ID associated with it.")
                        .contact(contact));
    }
}