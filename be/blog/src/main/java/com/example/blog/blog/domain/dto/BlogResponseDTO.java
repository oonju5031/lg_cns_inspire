package com.example.blog.blog.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogResponseDTO {
    private Integer blogId;
    private String title, content, email;
}
