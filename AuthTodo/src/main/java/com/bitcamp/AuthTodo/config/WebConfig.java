package com.bitcamp.AuthTodo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // spring bean으로 등록
public class WebConfig implements WebMvcConfigurer {
    private final long MAX_TIME_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedOriginPatterns("*").allowCredentials(true).allowedMethods("*").allowedHeaders("*");
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://192.168.219.132:3000")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(MAX_TIME_SECS);
    }
}
