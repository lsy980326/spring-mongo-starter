package com.template.spring_mongodb.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        // 토큰 확인이 필요없는 API를 체크해서 필터링하지 않음
        String requestUrl = request.getRequestURI();
        if (isWhiteListUrl(requestUrl)) {
            chain.doFilter(request, response);
            return;
        }

        final String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            System.out.println("토큰 확인"+header);
            processJwtToken(header.substring(7), request);
        }
        chain.doFilter(request, response);
    }

    private boolean isWhiteListUrl(String requestUrl) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        return Stream.of(SecurityConstants.WHITE_LIST)
            .anyMatch(pattern -> pathMatcher.match(pattern, requestUrl));
    }

    private void processJwtToken(String jwtToken, HttpServletRequest request) {
        String username = jwtUtil.extractUsername(jwtToken);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwtToken, userDetails)) {
                var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
    }
}