package com.template.spring_mongodb.config.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityConstants {
    public static final String[] WHITE_LIST = {
        "/*.html", "/**/*.js", "/**/*.css", "/static/**", "/resources/static/**",
        "/favicon.ico", "/public/**", "/actuator/**", "/v3/api-docs/**",
        "/docs", "/docs/**", "/swagger", "/swagger-ui/**",
        "/api-docs", "/api-docs/**",
        "/api/signup", "/api/v1/auth/login", "/api/v1/auth/refresh",
        "/files/**",
        "/api/policies", "/api/verify/**",
        "/api/check-username", "/api/find-username"
    };
}