package com.fif.exercisespring.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        // JWT bearer scheme
        SecurityScheme bearerScheme = new SecurityScheme()
                .name("bearerAuth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("Masukkan JWT token dari endpoint /api/auth/login. Format: Bearer {token}");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("bearerAuth");

        return new OpenAPI()
                .info(new Info()
                        .title("Mini Commerce Analytics API")
                        .version("1.0.0")
                        .description(
                            "REST API untuk Mini Commerce Analytics System.\n\n" +
                            "**Cara pakai Swagger:**\n" +
                            "1. Panggil `POST /api/auth/login` dengan body `{\"username\":\"admin\",\"password\":\"admin123\"}`\n" +
                            "2. Copy nilai `token` dari response\n" +
                            "3. Klik tombol **Authorize** di atas, paste token\n" +
                            "4. Semua request selanjutnya otomatis pakai JWT\n\n" +
                            "**Default credentials:**\n" +
                            "- Admin: `admin` / `admin123`\n" +
                            "- User: `user` / `user123`"
                        )
                        .contact(new Contact()
                                .name("Mini Commerce Team")
                        )
                )
                .addServersItem(new Server().url("http://localhost:8080").description("Local Dev"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", bearerScheme)
                )
                .addSecurityItem(securityRequirement);
    }
}