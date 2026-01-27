package com.example.blog.user.domain.dto;

import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private String email, password, name;
}