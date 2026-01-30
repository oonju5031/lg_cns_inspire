package com.example.blog_jpa.comment.domain.dto;

import com.example.blog_jpa.comment.domain.entity.CommentEntity;
import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {
    private Integer commentId;
    private String content;
    private Integer blogId;

    public static CommentResponseDTO fromEntity(CommentEntity entity) {
        return CommentResponseDTO.builder()
                .commentId(entity.getCommentId())
                .content(entity.getContent())
                .blogId(entity.getBlog().getBlogId())
                .build();
    }
}
