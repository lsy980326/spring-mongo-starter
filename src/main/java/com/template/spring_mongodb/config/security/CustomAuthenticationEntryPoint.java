package com.template.spring_mongodb.config.security;

import com.template.spring_mongodb.common.ApiResponseBody;
import com.template.spring_mongodb.common.Message;
import com.template.spring_mongodb.exception.AnasaException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        Throwable cause = authenticationException.getCause();
        int status = 401;
        String message = "error.security.access-denied";

        if (cause instanceof AnasaException ex) {
            status = ex.getStatus().value();
            message = ex.getMessage();
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status);
        String json = objectMapper.writeValueAsString(new ApiResponseBody<>(
            status,
            null,
            new Message(message),
            null
        ));
        response.getWriter().write(json);
    }
}