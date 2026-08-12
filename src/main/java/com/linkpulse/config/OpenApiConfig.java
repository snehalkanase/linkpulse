package com.linkpulse.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI linkPulseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Linkpulse API")
                        .version("0.0.1")
                        .description(
                                "Linkpulse Enterprise-Grade Multi-Tenant URL Shortening SaaS Platform with Real-time Analytics, Caching, and Scalable Architecture.")
                        .contact(new Contact()
                                .name("Snehal Kanase")
                                .email("[snehalkanase90@gmail.com]"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}