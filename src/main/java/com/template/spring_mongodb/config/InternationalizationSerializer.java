package com.template.spring_mongodb.config;

import com.template.spring_mongodb.common.Message;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.IOException;
import java.util.Locale;

@Slf4j
public class InternationalizationSerializer extends JsonSerializer<Message> {
    private final MessageSource messageSource;

    public InternationalizationSerializer(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void serialize(Message value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        Locale locale = LocaleContextHolder.getLocale();
        Object[] args = value.getArguments() == null ? new Object[]{} : value.getArguments().toArray();
        gen.writeString(messageSource.getMessage(value.getMessage(), args, value.getMessage(), locale));
    }
}
