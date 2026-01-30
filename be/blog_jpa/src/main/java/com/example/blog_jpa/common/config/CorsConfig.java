package com.example.blog_jpa.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedOriginPatterns("http://localhost:3000")  // Front에서 들어오는 end point를 허용
                .allowedMethods("*")  // 모든 HTTP method를 허용
                .allowCredentials(true);  // 인증 허용
    }
}
