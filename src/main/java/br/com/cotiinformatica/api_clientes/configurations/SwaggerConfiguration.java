package br.com.cotiinformatica.api_clientes.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    /*
     * Método para configurar a documentação do Swagger
     * Título: API Clientes - Treinamento COTI Informática
     * Autor: COTI Informática
     * Versão: 1.0
     */
    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("API Clientes - Treinamento COTI Informática")
                                .description("API REST para gerenciamento de clientes.")
                                .version("1.0")
                                .contact(
                                        new Contact()
                                                .name("COTI Informática")
                                )
                );
    }
}