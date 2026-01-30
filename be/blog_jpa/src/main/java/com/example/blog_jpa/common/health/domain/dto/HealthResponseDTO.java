package com.example.blog_jpa.common.health.domain.dto;

import com.example.blog_jpa.common.health.domain.entity.HealthEntity;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HealthResponseDTO {
    private Long id;
    private String message;

    public static HealthResponseDTO fromEntity(HealthEntity entity) {
        return HealthResponseDTO.builder()
                .id(entity.getId())
                .message(entity.getMessage())
                .build();
    }
}
