package com.template.spring_mongodb.config;

import com.template.spring_mongodb.common.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Slf4j
@Configuration
@AllArgsConstructor
public class JacksonConfig {
    private final MessageSource messageSource;

    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        log.info("JacksonConfig is starting.");
        builder.propertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
        builder.modules(timeModule(), translationModule());
        return builder.build();
    }

    private JavaTimeModule timeModule() {
        return new JavaTimeModule();
    }

    private SimpleModule translationModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Message.class, new InternationalizationSerializer(messageSource));
        return module;
    }
}