package com.example.blog_jpa.comment.domain.dto;

import com.example.blog_jpa.blog.domain.entity.BlogEntity;
import com.example.blog_jpa.comment.domain.entity.CommentEntity;
import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDTO {
    private String content;
    private Integer blogId;

    public CommentEntity toEntity(BlogEntity blog) {
        return CommentEntity.builder()
                .content(this.content)
                .blog(blog)
                .build();
    }
}
