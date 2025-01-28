package com.accenture_project.order.configs.swagger;

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
        contact.setName("Lucas Matheus Gomes de Lima");
        contact.setEmail("lucazmatehus14@gmail.com");

        return new OpenAPI().info(new Info()
                        .title("Pedido API")
                        .version("1.0")
                        .description("O Microserviço de Pedido é responsável por criar e gerenciar pedidos. " +
                                "Este serviço envia os pedidos para filas que os microsserviços de estoque e pagamento consomem, utilizando a exchange do tipo fanout para garantir que as mensagens sejam transmitidas para ambos os serviços. " +
                                "Além disso, ele possui um endpoint para processar o pagamento do pedido, enviando o código do pedido e o valor para o microsserviço de pagamento, utilizando a exchange do tipo direct para garantir uma comunicação direta. " +
                                "O código ele recebe via email, caso o pedido seja confirmado.")
                        .contact(contact));
    }
}