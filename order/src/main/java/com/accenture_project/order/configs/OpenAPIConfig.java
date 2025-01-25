package com.accenture_project.order.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pedido API")
                        .version("1.0")
                        .description("API para gerenciamento de pedidos, integração com sistemas de pagamento e sistema de estoque, garantindo um fluxo automatizado para o processamento de pedidos."));
    }
}