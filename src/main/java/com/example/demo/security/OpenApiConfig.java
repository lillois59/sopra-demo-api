package com.example.demo.security;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sopra Steria Demo API")
                        .version("1.0")
                        .description("Demo API pour Sopra Steria - Secteur Défense"))
                .addServersItem(new Server().url("https://sopra-demo-api-production.up.railway.app"));
    }
}