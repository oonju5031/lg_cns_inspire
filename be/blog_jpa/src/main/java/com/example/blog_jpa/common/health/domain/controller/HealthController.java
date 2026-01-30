package com.example.blog_jpa.common.health.domain.controller;

import com.example.blog_jpa.common.health.domain.dto.HealthResponseDTO;
import com.example.blog_jpa.common.health.domain.service.HealthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/health")
@RequiredArgsConstructor
public class HealthController {

    private final HealthService healthService;

    @GetMapping("/create")
    public ResponseEntity<HealthResponseDTO> create() {
        log.info(">>> create");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(healthService.create());
    }

    @GetMapping("/read/{blogId}")
    public ResponseEntity<HealthResponseDTO> read(@PathVariable Long blogId) {
        log.info(">>> read {}", blogId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(healthService.read(blogId));
    }

    @GetMapping("/update/{blogId}")
    public ResponseEntity<HealthResponseDTO> update(@PathVariable Long blogId) {
        log.info(">>> update {}", blogId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(healthService.update(blogId));
    }

    @GetMapping("/delete/{blogId}")
    public ResponseEntity<Boolean> delete(@PathVariable Long blogId) {
        log.info(">>> delete {}", blogId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(healthService.delete(blogId));
    }

}
