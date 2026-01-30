package com.example.blog_jpa.common.health.domain.service;

import com.example.blog_jpa.common.health.domain.dto.HealthResponseDTO;
import com.example.blog_jpa.common.health.domain.entity.HealthEntity;
import com.example.blog_jpa.common.health.repository.HealthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HealthService {

    private final HealthRepository healthRepository;

    public HealthResponseDTO create() {
        // insert
        HealthEntity entity = healthRepository.save(HealthEntity.builder().message("create test").build());

        return HealthResponseDTO.fromEntity(entity);
    }

    public HealthResponseDTO read(Long blogId) {

        // 방법 1. 기본
        // Optional<HealthEntity> find = healthRepository.findById(blogId);
        // 방법 2. entity를 바로 생성
        HealthEntity entity = healthRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Read Failed."));

        return HealthResponseDTO.fromEntity(entity);
    }

    public HealthResponseDTO update(Long blogId) {

        HealthEntity entity = healthRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Read Failed."));

        HealthEntity updated = HealthEntity.builder()
                                        .id(entity.getId())
                                        .message("updated.")
                                        .build();

        HealthEntity result = healthRepository.save(updated);

        return HealthResponseDTO.fromEntity(result);
    }

    public boolean delete(Long blogId) {

        healthRepository.deleteById(blogId);

        return true;
    }

}
