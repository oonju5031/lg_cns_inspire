package com.example.blog_jpa.comment.repository;

import com.example.blog_jpa.comment.domain.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findByBlog_BlogId(Integer blogId);
}
