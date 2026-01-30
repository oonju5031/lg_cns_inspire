package com.example.blog_jpa.blog.domain.dto;

import com.example.blog_jpa.blog.domain.entity.BlogEntity;
import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BlogResponseDTO {
    private Integer blogId;
    private String title, content, email;

    public static BlogResponseDTO fromEntity(BlogEntity entity) {
        return BlogResponseDTO.builder()
                .blogId(entity.getBlogId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .email(entity.getAuthor().getEmail())
                .build();
    }
}
