package com.example.blog.comment.domain.dto;

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
}