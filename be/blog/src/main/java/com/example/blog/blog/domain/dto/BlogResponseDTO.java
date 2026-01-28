package com.example.blog.blog.domain.dto;

import com.example.blog.comment.domain.dto.CommentResponseDTO;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BlogResponseDTO {
    private Integer blogId;
    private String title, content, email;

    private List<CommentResponseDTO> comments;
}
