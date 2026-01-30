package com.example.blog_jpa.blog.repository;

import com.example.blog_jpa.blog.domain.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {

}
