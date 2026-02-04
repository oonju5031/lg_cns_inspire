package com.example.blog_jpa.blog.domain.entity;

import com.example.blog_jpa.comment.domain.entity.CommentEntity;
import com.example.blog_jpa.user.domain.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jpa_blog")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blogId;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(length = 1000)
    private String content;

    // 외래 키
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "email")
    private UserEntity author;

    // FK 정책: 기본(RESTRICT) -> 댓글이 있는 게시글은 삭제 불가능
//    @OneToMany(mappedBy = "blog", orphanRemoval = false)
//    private List<CommentEntity> comments = new ArrayList<>();

    // FK 정책: CASCADE -> 댓글이 있는 게시글 삭제 시 해당 게시글의 댓글도 삭제
    @OneToMany( mappedBy = "blog",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<CommentEntity> comments = new ArrayList<>();

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
