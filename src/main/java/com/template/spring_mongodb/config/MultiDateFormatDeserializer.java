package com.template.spring_mongodb.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MultiDateFormatDeserializer extends JsonDeserializer<LocalDate> {

    private static final DateTimeFormatter FORMATTER_1 = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter FORMATTER_2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String date = p.getText();

        try {
            return LocalDate.parse(date, FORMATTER_1);
        } catch (DateTimeParseException e) {
            // Continue to next format
        }

        try {
            return LocalDate.parse(date, FORMATTER_2);
        } catch (DateTimeParseException e) {
            // Continue to next format
        }

        throw ctxt.weirdStringException(date, LocalDate.class, "Failed to parse date: " + date);
    }
}