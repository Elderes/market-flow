package br.com.accenture_project.payments.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * OpenAPIConfig Class
 *
 * This class configures Swagger documentation for the "Pagamento API" microservice.
 * It defines API metadata such as title, version, description, and contact information.
 * The customOpenAPI() method creates a bean that integrates this configuration into the Spring Boot application.
 *
 */

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Pedido API")
                .version("1.0")
                .description("\"Payment Service API allows managing and processing payments for orders.\\n\\n\" +\n" +
                        "\"POST /pay: Initiates a payment for an order. The payment details are provided in the request body, and the service checks if the payment is valid and processes it. If successful, the payment status is updated, and a message is sent to the payment queue.\\n\\n\" +\n" +
                        "\"GET /payments: Retrieves a list of all payments in the system.\\n\\n\" +\n" +
                        "\"GET /payment/{id}: Retrieves the payment details for a specific payment by its ID.\\n\\n\" +\n" +
                        "\"DELETE /payment/{id}: Deletes a payment record by its ID.\\n\\n\" +\n" +
                        "\"The service also includes email notifications sent to customers regarding their payment status (approved or rejected) and payment errors.\"\n"));
    }
}