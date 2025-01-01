package com.template.spring_mongodb.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Value("${swagger.local.url}")
    private String localUrl;

    @Value("${swagger.external.url}")
    private String externalUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .addSecurityItem(
                new SecurityRequirement().addList("JWT Authentication"))
            .components(
                new Components()
                    .addSecuritySchemes("JWT Authentication",
                        new SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")))
            .info(
                new Info()
                    .title("Ulvac Cr API")
                    .description("한국알박 클린룸 API 문서")
                    .version("v1")
            )
            .addServersItem(new Server().url(externalUrl).description("External Server"))
            .addServersItem(new Server().url(localUrl).description("Local Server"))
            .addServersItem(new Server().url("http://192.168.228.136:9001").description("내부용"));

    }

    @Bean
    public GroupedOpenApi mobile() {
        return GroupedOpenApi.builder()
            .group("api mobile v1")
            .pathsToMatch("/api/v1/m/**")
            .build();
    }

    @Bean
    public GroupedOpenApi v1Api() {
        return GroupedOpenApi.builder()
            .group("api v1")
            .pathsToMatch("/api/**")
            .build();
    }

    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper) {
        return new ModelResolver(objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE));
    }
}
