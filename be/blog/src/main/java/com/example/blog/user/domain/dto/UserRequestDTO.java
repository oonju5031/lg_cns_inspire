package com.example.blog.user.domain.dto;

import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String email, password, name;
}