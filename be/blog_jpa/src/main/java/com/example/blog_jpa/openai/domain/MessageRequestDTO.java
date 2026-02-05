package com.example.blog_jpa.openai.domain;

import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestDTO {
    private String message;
}
