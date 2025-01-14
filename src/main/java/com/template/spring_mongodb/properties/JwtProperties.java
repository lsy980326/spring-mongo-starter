package com.template.spring_mongodb.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties("jwt")
public class JwtProperties {
    private String secret;
    private Long expirationTime;
    private Long refreshExpirationTime;
}
