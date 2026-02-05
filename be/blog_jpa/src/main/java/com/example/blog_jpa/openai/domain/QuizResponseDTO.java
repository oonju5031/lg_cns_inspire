package com.example.blog_jpa.openai.domain;

import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuizResponseDTO {
    private List<Quiz> quizzes;

    @Builder
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Quiz {
        private String question;
        private List<String> options;
        private String answer;
        private String desc;
    }

}
