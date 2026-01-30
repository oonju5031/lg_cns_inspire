package com.example.blog_jpa.user.domain.entity;

import com.example.blog_jpa.blog.domain.entity.BlogEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jpa_user")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private String email;

    @Column(nullable = false, length = 200)
    private String password;

    private String name;
    private String role;

    @OneToMany(mappedBy = "author", orphanRemoval = false)
    private List<BlogEntity> blogs = new ArrayList<>();

}
