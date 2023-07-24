package com.miguel.prueba.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI jsltOpenApi() {
        return new OpenAPI()
                .info(new Info().title("ForgeRock Test")
                        .description("ForgeRock Test - Miguel Gutiérrez")
                        .version("1.0"));
    }

}
