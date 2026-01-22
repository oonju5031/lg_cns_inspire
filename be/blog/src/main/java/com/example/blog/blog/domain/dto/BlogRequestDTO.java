package com.example.blog.blog.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogRequestDTO {
    private String title, content, email;
}
