package com.mineirinho.mercado.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    //http://localhost:8083/swagger-ui/index.html#/
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API RESTful")
                        .version("v1")
                        .description("Descrição da API")
                        .termsOfService("Termo de Serviço")
                        .license(new License().name("Apache 2.0")
                                .url("URL")));
    }
}
