package com.example.blog_jpa.openai.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatResponseDTO {
    private String weather;
    private String location;
    private List<Restaurant> restaurantList;

    @Builder
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Restaurant {
        private String name;
        private String category;
        private String reason;
    }
}
