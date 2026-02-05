package com.example.blog_jpa.openai.service;

import com.example.blog_jpa.openai.domain.MessageRequestDTO;
import com.example.blog_jpa.openai.domain.MessageResponseDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

// 3세대 API를 이용한 서비스
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

    @Value("${spring.ai.openai.model}")
    private String model;
    @Value("${spring.ai.openai.api.key}")
    private String key;
    @Value("${spring.ai.openai.api.url}")
    private String url;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final WebClient openWebClient;

    public MessageResponseDTO ask(MessageRequestDTO requestDTO) {
        log.info(">>> MessageService ask: {}", requestDTO.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(key);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Response API Body
        Map<String, Object> body = Map.of(
                "model", model,
                "input", requestDTO.getMessage()
        );

        HttpEntity<?> request = new HttpEntity<>(body, headers);
        String response = restTemplate.postForObject(url + "/responses", request, String.class);

        log.info(">>> response: {}", response);

        JsonNode node = null;
        String responseJson = null;

        try {
            node = objectMapper.readTree(response);
            node.path("output")
                    .get(0)
                    .path("content")
                    .get(0)
                    .path("text")
                    .asText();

            log.info(">>> responseJson: {}", responseJson);

            return MessageResponseDTO.builder()
                    .answer(responseJson)
                    .build();

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return null;
    }

    // webflux version
    public Mono<MessageResponseDTO> generateMessage(MessageRequestDTO requestDTO) {
        log.info(">>> MessageService generateMessage: {}", requestDTO.getMessage());

        String prompt = """
                인간의 감성을 지니고 있는 모델로서,
                입력된 키워드를 바탕으로 멋진 환영 메시지를 만들어라.
                backtick(`)이 있으면 제거하라.
                키워드: %s
                """.formatted(requestDTO.getMessage());

        Map<String, Object> body = Map.of(
                "model", model,
                "input", prompt,
                "max_output_tokens", 200  // 단어 수 제한
        );

        return openWebClient.post()
                .uri("/responses")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + key)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::jsonParse)
                .map(txt -> MessageResponseDTO.builder().answer(txt).build());
    }

    public String jsonParse(String response) {
        JsonNode node = null;
        String responseJson = null;

        try {
            node = objectMapper.readTree(response);
            responseJson = node.path("output")
                    .get(0)
                    .path("content")
                    .get(0)
                    .path("text")
                    .asText();

            log.info(">>> responseJson: {}", responseJson);

            return responseJson;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
