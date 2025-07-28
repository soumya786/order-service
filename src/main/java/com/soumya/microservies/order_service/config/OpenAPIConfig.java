package com.soumya.microservies.order_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI orderServiceAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Order Service API")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0"))
                        .description("API documentation for the Order Service"))
                .externalDocs(new io.swagger.v3.oas.models.ExternalDocumentation()
                        .description("Order Service Documentation")
                        .url("https://order-service.example.com/docs"));
    }
}
