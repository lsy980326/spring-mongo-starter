package com.template.spring_mongodb.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {
//    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        var user = userService.getCurrentUser();
//        if (user == null) {
//            log.warn("Unauthorized access attempt detected.");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        } else {
//
////        if (!userService.checkUserAccess(user, request.getRequestURI())) {
////            log.warn("User '{}' attempted to access a protected API: {}", user.getUsername(), request.getRequestURI());
////            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
////            return false;
////        }
//
//            String params = request.getParameterMap().entrySet().stream()
//                .map(entry -> entry.getKey() + "=" + Arrays.toString(entry.getValue()))
//                .collect(Collectors.joining(", "));
//
////            log.info("User(id=[{}], username=[{}]) called API: [{} {}], with parameters: {}",
////                user.getId(), user.getUsername(), request.getMethod(), URLDecoder.decode(request.getRequestURI(), StandardCharsets.UTF_8), params);
//        }

        return true;
    }
}
