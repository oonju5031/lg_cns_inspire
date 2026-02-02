package com.example.blog_jpa.user.domain.dto;

import com.example.blog_jpa.user.domain.entity.UserEntity;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String email;
    private String password;
    private String name;
    private String role;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .role(this.role != null ? this.role : "default role")
                .build();
    }
}
