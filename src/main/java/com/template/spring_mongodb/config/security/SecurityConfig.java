package com.template.spring_mongodb.config.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Slf4j  // 로깅을 위한 Lombok 어노테이션으로, log 객체를 생성하여 로그 출력을 도와줍니다.
@Configuration  // 스프링 설정 클래스로, 빈(bean) 설정 및 의존성 주입을 정의하는 클래스임을 나타냅니다.
@EnableWebSecurity  // 스프링 시큐리티를 활성화하는 어노테이션으로, 시큐리티 관련 설정을 적용합니다.
@RequiredArgsConstructor  // final 필드에 대해 생성자를 자동 생성하는 Lombok 어노테이션으로, 필드 주입 대신 생성자 주입을 사용합니다.
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

//    private final CustomUserDetailsService userDetailsService; // 사용자 세부 정보 로드를 위한 CustomUserDetailsService
//    private final ObjectMapper objectMapper; // JSON 직렬화/역직렬화를 위한 Jackson의 ObjectMapper 객체
//    private final JwtUtil jwtUtil; // JWT 토큰 생성 및 검증을 위한 유틸리티 클래스
//
//    /**
//     * 프로덕션 환경에 적용할 보안 설정을 구성합니다.
//     * @param http HttpSecurity 객체로, 시큐리티 필터 체인을 설정하기 위해 사용됩니다.
//     * @return SecurityFilterChain 시큐리티 필터 체인으로, 스프링 시큐리티의 필터 동작을 정의합니다.
//     */
//    @Bean
//    @Profile({"prod", "product", "production"})  // 이 빈은 'prod', 'product', 'production' 프로파일에서만 활성화됩니다.
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        log.info("Security on production"); // 프로덕션 환경에서 보안 설정이 활성화되었음을 로그로 기록합니다.
//
//        // CORS 설정 - 모든 출처에서의 요청을 허용합니다.
//        http.cors(cors -> cors.configurationSource(corsConfigurationSource(
//                "http://localhost:9001",
//                "https://api.anasa-app.com",
//                "http://125.208.85.11:9001",
//                "http://192.168.228.136",
//                "http://192.168.215.55",
//                "http://access.inside.ulvackorea.co.kr")));
//
//        // 세션을 사용하지 않도록 설정하여 JWT 토큰만으로 인증을 처리합니다.
//        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        // CSRF 보호 기능을 비활성화합니다. REST API나 JWT 인증을 사용할 때는 CSRF가 필요하지 않습니다.
//        http.csrf(AbstractHttpConfigurer::disable);
//
//        // 접근 권한 설정
//        http.authorizeHttpRequests(accessManagement -> accessManagement
//            .requestMatchers(SecurityConstants.WHITE_LIST).permitAll() // 허용된 URL에 대해 인증 없이 접근 가능하게 설정합니다.
//            .requestMatchers("/api/v1/base/**").permitAll()
//            .requestMatchers("/api/v1/monitoring/**").permitAll()
//            .requestMatchers("/api/v1/m/**").permitAll()
//            .anyRequest().authenticated() // 그 외의 요청은 인증된 사용자만 접근할 수 있습니다.
//        );
//
//        // JWT 토큰 필터를 UsernamePasswordAuthenticationFilter 전에 추가하여 토큰 기반 인증을 수행합니다.
//        http.addFilterBefore(new JwtTokenFilter(jwtUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class);
//
//        // 인증 실패 시 CustomAuthenticationEntryPoint를 통해 JSON 형식으로 에러를 응답합니다.
//        http.exceptionHandling(handle -> handle.authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper)));
//
//        // 설정이 완료된 SecurityFilterChain 객체를 반환합니다.
//        return http.build();
//    }
//
//
//    @Bean
//    @Profile({"local", "test", "dev", "develop", "development"})
//    public SecurityFilterChain filterChainForDevelop(HttpSecurity http) throws Exception {
//        log.info("Security on develop");
//        System.out.println("여기 돌았나..?22222222222");
//
//        http.cors(cors -> cors.configurationSource(corsConfigurationSource(
//            "http://localhost:3000",
//            "http://localhost:5173",
//            "https://api.anasa-app.com",
//            "https://main.d3uin5crfxvgj3.amplifyapp.com",
//            "http://192.168.0.66:8080",
//            "http://access.inside.ulvackorea.co.kr"
//        )));
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        http.authorizeHttpRequests(accessManagement -> accessManagement
//            .requestMatchers(SecurityConstants.WHITE_LIST).permitAll()
//            .requestMatchers("/api/v1/base/**").permitAll()
//            .requestMatchers("/api/v1/monitoring/**").permitAll()
//            .anyRequest().authenticated()
//        );
//
//        http.addFilterBefore(new JwtTokenFilter(jwtUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class);
//        http.exceptionHandling(handle -> handle.authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper)));
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    private UrlBasedCorsConfigurationSource corsConfigurationSource(String... origins) {
//        final var configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of(origins));
//        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Language", "Content-Length", "Content-Type"));
//        configuration.setExposedHeaders(List.of("Authorization"));
//        configuration.setAllowCredentials(true);
//
//        final var source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @Bean
    @Profile({"local", "test", "dev", "develop", "development"})
    public SecurityFilterChain filterChainForDevelop(HttpSecurity http) throws Exception {
        log.info("Security on develop - All requests permitted");
        System.out.println("여기 돌았나..?22222222222");

        // 모든 요청 허용
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(accessManagement -> accessManagement.anyRequest().permitAll());

        return http.build();
    }


}