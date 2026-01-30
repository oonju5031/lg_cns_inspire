package com.example.blog_jpa.user.repository;

import com.example.blog_jpa.user.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByEmailAndPassword(String email, String password);
}
