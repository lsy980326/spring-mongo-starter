package com.template.spring_mongodb.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomLocaleResolver implements LocaleResolver {
    private Locale parseLocale(String locale) {
        if (!StringUtils.hasText(locale)) return null;

        try {
            return StringUtils.parseLocale(locale);
        } catch (Exception e) {
            return null;
        }
    }

    @NonNull
    @Override
    public Locale resolveLocale(@NonNull HttpServletRequest request) {
        String language = request.getHeader("Accept-Language");
        return Optional.ofNullable(language)
            .map(this::parseLocale)
            .orElseGet(request::getLocale);
    }

    @Override
    public void setLocale(@NonNull HttpServletRequest request, HttpServletResponse response, Locale locale) {
        throw new UnsupportedOperationException("Not implemented.");
    }
}
