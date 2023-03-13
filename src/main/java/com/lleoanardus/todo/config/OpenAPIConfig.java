package com.lleoanardus.todo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    OpenAPI openAPI(){
        return new OpenAPI().info(new Info().version("1.0.0").title("To-do List API")
                .description("API to help you better manage your tasks"));
    }
}
