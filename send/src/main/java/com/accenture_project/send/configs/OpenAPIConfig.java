package com.accenture_project.send.configs;

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
                        .title("Envio API")
                        .version("1.0")
                        .description("O Microserviço de Envio de Pedido é responsável por enviar notificações de confirmação de pedidos aos clientes, informando que seus pedidos estão sendo processados e enviados. " +
                                "Ele consome as mensagens na fila de pagamento, envia e-mails para os clientes confirmando os detalhes do pedido, e oferece um endpoint para visualizar todos os pedidos que foram finalizados com sucesso. " +
                                "Este serviço está integrado com o sistema de filas para processar a comunicação assíncrona, garantindo que as notificações sejam enviadas sem impactar diretamente o fluxo de trabalho do sistema de pedidos."));
    }
}