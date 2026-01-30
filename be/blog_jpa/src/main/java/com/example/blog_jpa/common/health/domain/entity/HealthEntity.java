package com.example.blog_jpa.common.health.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "health")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HealthEntity {

    @Id  // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AUTO_INCREMENT
    private Long id;

    private String message;

}
