package com.example.blog_jpa.common.health.repository;

import com.example.blog_jpa.common.health.domain.entity.HealthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRepository extends JpaRepository<HealthEntity, Long> {  // <영속성을 관리하는 Entity, 그 Entity의 PK 타입>

}
