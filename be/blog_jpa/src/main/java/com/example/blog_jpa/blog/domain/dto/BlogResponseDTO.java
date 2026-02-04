package com.example.blog_jpa.blog.domain.dto;

import com.example.blog_jpa.blog.domain.entity.BlogEntity;
import com.example.blog_jpa.comment.domain.dto.CommentResponseDTO;
import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BlogResponseDTO {
    private Integer blogId;
    private String title, content, email;
    private List<CommentResponseDTO> comments;

    public static BlogResponseDTO fromEntityWithoutComments(BlogEntity entity) {
        return BlogResponseDTO.builder()
                .blogId(entity.getBlogId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .email(entity.getAuthor().getEmail())
                .build();
    }

    public static BlogResponseDTO fromEntityWithComments(BlogEntity entity) {
        return BlogResponseDTO.builder()
                .blogId(entity.getBlogId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .email(entity.getAuthor().getEmail())
                .comments(
                        entity.getComments()
                                .stream()
                                .map(CommentResponseDTO::fromEntity)
                                .toList()
                )
                .build();
    }
}
