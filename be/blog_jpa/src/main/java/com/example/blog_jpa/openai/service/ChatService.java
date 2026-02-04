package com.example.blog_jpa.openai.service;

import com.example.blog_jpa.openai.domain.ChatResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2세대 API를 사용한 서비스
@Slf4j
@Service
public class ChatService {

    @Value("${spring.ai.openai.model}")
    private String model;
    @Value("${spring.ai.openai.api.key}")
    private String key;
    @Value("${spring.ai.openai.api.url}")
    private String url;

    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ChatResponseDTO recommend(String weather, String location) throws IOException {
        log.info(">>> ChatService recommend");
        log.info(">>> params weather: {}, location: {}", weather, location);

        String prompt = """
                오늘 날씨에 적합한 맛있는 음식점을 추천받고자 한다.
                아래 규칙을 반드시 준수하라.
                1. 무조건 json 형식으로 대답할.
                2. 다른 문장이나 설명 없이 json만으로 응답하라.
                조건:
                - 날씨: "%s"
                - 위치: "%s"
                출력 예시:
                    {
                        "weather": "<날씨>",
                        "location": "<위치>",
                        "restaurants": [
                            {"name": "<음식점명>", "category": "<분류>", "reason": "<추천 이유>"}, ...
                        ]
                    }
                """.formatted(weather, location);

        Map<String, Object> systemRole = new HashMap<>();
        systemRole.put("role", "system");
        systemRole.put("content", "너는 맛집 추천 전문가야.");

        Map<String, Object> userRole = new HashMap<>();
        userRole.put("role", "user");
        userRole.put("content", prompt);

        Map<String, Object> message = new HashMap<>();
        message.put("model", model);
        message.put("messages", List.of(systemRole, userRole));

        String requestJson = null;

        try {
            requestJson = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            log.debug(e.getMessage());
        }

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer " + key)
                .header("Content-Type", "application/json")
                .post(RequestBody.create(requestJson, MediaType.parse("application/json")))
                .build();

        Response response = null;
        String responseJson = null;

        try {
            response = okHttpClient.newCall(request).execute();
            log.info(">>> response: {}", response);

            responseJson = response.body().string();
            log.info(">>> responseJson: {}", responseJson);

            JsonNode node = objectMapper.readTree(responseJson);
            log.info(">>> node: {}", node);

            String exr = node.at("/choices/0/message/content").asText();
            log.info(">>> exr: {}", exr);

            return objectMapper.readValue(exr, ChatResponseDTO.class);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return null;
    }
}
