package com.example.blog_jpa.blog.repository;

import com.example.blog_jpa.blog.domain.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {

    // JPQL (Java Persistence Query Language) 사용하여 성능 최적화
    @Query("""
            SELECT          b
            FROM            BlogEntity b
            LEFT JOIN FETCH b.comments
            WHERE           b.blogId = :blogId
            """)
    Optional<BlogEntity> findWithComments(@Param("blogId") Integer blogId);
}
