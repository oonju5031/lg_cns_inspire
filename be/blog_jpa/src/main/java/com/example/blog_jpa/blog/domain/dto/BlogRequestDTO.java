package com.example.blog_jpa.blog.domain.dto;

import com.example.blog_jpa.blog.domain.entity.BlogEntity;
import com.example.blog_jpa.user.domain.entity.UserEntity;
import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BlogRequestDTO {
    private String title, content;
    private String email;

    public BlogEntity toEntity(UserEntity user) {
        return BlogEntity.builder()
                .title(this.title)
                .content(this.content)
                .author(user)
                .build();
    }
}
