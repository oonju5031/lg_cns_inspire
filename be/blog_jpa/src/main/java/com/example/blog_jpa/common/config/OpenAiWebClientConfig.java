package com.example.blog_jpa.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OpenAiWebClientConfig {

    @Value("${spring.ai.openai.api.url}")
    private String url;

    @Bean
    public WebClient openWebClient() {
        return WebClient.builder()
                .baseUrl(url)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}