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
@ConfigurationProperties("coolsms.api")
public class SmsProperties {
    private String domain = "https://api.coolsms.co.kr";
    private String key;
    private String secret;
    private String id;
    private String fromNumber = "0317451230";
    private Template template;

    @Getter
    @Setter
    @ToString
    public static class Template {
        private String id;
    }
}
