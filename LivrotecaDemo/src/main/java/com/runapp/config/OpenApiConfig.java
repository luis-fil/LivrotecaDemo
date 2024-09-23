package com.runapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Livroteca")
                .version("1.0")
                .description("API para gerenciamento de livros e fóruns")
                .contact(new Contact()
                    .name("José Matheus, Letícia Baracho e Luís Filipe")
                    .email("leticia.baracho@ufape.edu.br")));
    }
}