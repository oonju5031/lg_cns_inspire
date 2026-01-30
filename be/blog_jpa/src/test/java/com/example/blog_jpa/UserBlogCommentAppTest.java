package com.example.blog_jpa;

import com.example.blog_jpa.blog.domain.entity.BlogEntity;
import com.example.blog_jpa.blog.repository.BlogRepository;
import com.example.blog_jpa.comment.domain.dto.CommentRequestDTO;
import com.example.blog_jpa.comment.domain.dto.CommentResponseDTO;
import com.example.blog_jpa.comment.domain.entity.CommentEntity;
import com.example.blog_jpa.comment.repository.CommentRepository;
import com.example.blog_jpa.user.domain.dto.UserRequestDTO;
import com.example.blog_jpa.user.domain.entity.UserEntity;
import com.example.blog_jpa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
public class UserBlogCommentAppTest {
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    /*
    테스트케이스 시나리오
    - 1. 로그인한 사용자(User) 존재
    - 2. 로그인한 사용자가 Blog 작성 (author)
    - 3. Blog에 Comment 작성
    - 4. Blog 조회(Blog-User, Blog-Comment)
    - 5. Comment 삭제
     */
    @Test
    public void userCreate() {
        UserRequestDTO request = UserRequestDTO.builder()
                .email("user@user.com")
                .password("pwd")
                .name("관리자")
                .role("ADMIN")
                .build();

        UserEntity result = userRepository.save(request.toEntity());

    }

    @Test
    public void createCommentWithBlog() {
        System.out.println(">>> 블로그에 댓글 작성");

        CommentRequestDTO request = CommentRequestDTO.builder()
                .content("댓글 내용")
                .blogId(1)
                .build();

        BlogEntity blog = blogRepository.findById(request.getBlogId())
                .orElseThrow(() -> new RuntimeException("Blog Not Exists."));

        CommentEntity response = commentRepository.save(request.toEntity(blog));

        System.out.println(CommentResponseDTO.fromEntity(response));
    }


}
