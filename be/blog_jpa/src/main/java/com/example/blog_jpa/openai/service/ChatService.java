package com.example.blog_jpa.openai.service;

import com.example.blog_jpa.openai.domain.ChatResponseDTO;
import com.example.blog_jpa.openai.domain.QuizResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2세대 API를 사용한 서비스
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    @Value("${spring.ai.openai.model}")
    private String model;
    @Value("${spring.ai.openai.api.key}")
    private String key;
    @Value("${spring.ai.openai.api.url}")
    private String url;

    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate;

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

    public QuizResponseDTO quiz(String subject) {
        log.info(">>> MessageService quiz: {}", subject);

        String prompt = """
                당신은 국가 공인 문제 출제 위원 전문가입니다.
                당신의 전공 분야는 %s입니다.
                아래 규칙을 지키십시오.
                1. 무조건 json 형식으로 대답하십시오.
                2. 다른 문장이나 설명 없이 json으로만 출력하십시오.
                3. 당신의 전공 분야와 관련된 3개의 퀴즈를 만드십시오.
                4. backtick(`)을 사용하지 마십시오.
                
                출력 예시:
                    {
                        "quizzes": [
                            {
                                "quiz": "<문제 내용>",
                                "options": ["보기 1", "보기 2", "보기 3", "보기 4"],
                                "answer": "<정답>",
                                "description": "<해설">
                            }
                        ]
                    }
                """.formatted(subject);

        Map<String, Object> systemRole = new HashMap<>();
        systemRole.put("role", "system");
        systemRole.put("content", "당신은 국가 공인 문제 출제 위원 전문가입니다.");

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

        log.info(">>> requestJson: {}", requestJson);

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

        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return null;
    }
}
