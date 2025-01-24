package com.accenture_projeto.buyer.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Comprador API")
                        .version("1.0")
                        .description("""
                                A Buyer API fornece endpoints para gerenciar compradores e seus pedidos. Ela permite que você crie, atualize, recupere e exclua registros de compradores, bem como lide com operações relacionadas a pedidos de produtos.
                                
                                Os principais recursos incluem:
                                
                                - Gerenciamento de compradores (operações CRUD).
                                - Gerenciamento de pedidos de compradores.
                                - Processamento de pedidos e integração com filas de mensagens (RabbitMQ).
                                - Tratamento abrangente de erros e validação de entrada.
                                """));
    }
}
