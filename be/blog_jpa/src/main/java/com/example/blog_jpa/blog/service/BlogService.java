package com.example.blog_jpa.blog.service;

import com.example.blog_jpa.blog.domain.dto.BlogRequestDTO;
import com.example.blog_jpa.blog.domain.dto.BlogResponseDTO;
import com.example.blog_jpa.blog.domain.entity.BlogEntity;
import com.example.blog_jpa.blog.repository.BlogRepository;
import com.example.blog_jpa.comment.domain.dto.CommentResponseDTO;
import com.example.blog_jpa.comment.repository.CommentRepository;
import com.example.blog_jpa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BlogService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public BlogResponseDTO write(BlogRequestDTO requestDTO) {
        log.info(">>> BlogService write");

        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String email = auth.getName();

        log.info(">>> BlogService auth email: {}", email);

        return userRepository.findById(email)
                .map(user -> {
                    BlogEntity blog = blogRepository.save(requestDTO.toEntity(user));
                    return BlogResponseDTO.fromEntityWithoutComments(blog);
                })
                .orElseThrow(() -> new RuntimeException("사용자 인증 오류"));
    }

    @Transactional(readOnly = true)
    public BlogResponseDTO read(int blogId) {
        log.info(">>> BlogService read: {}", blogId);

        /*
        성능을 위해 findWithComments엔 JPQL을 사용한다. -> Repository 주석 참고
        - JPQL 미사용: 해당 블로그가 보유한 각각의 comment마다 query 발생 -> 다수의 query 실행하여 성능 저하
            - 코드:
                BlogEntity blog = BlogRepository.findById(id).get();
                List<CommentEntity> comments = blog.getComments();
        - JPQL 사용  : 해당 블로그가 보유한 comment들을 query 하나로 전부 가져옴 -> 성능 최적화
        */
        return blogRepository.findWithComments(blogId)
                .map(BlogResponseDTO::fromEntityWithComments)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));
    }


    /*
    삭제의 경우 참조 무결성을 고려하여야 한다.
    기본은 RESTRICT
    */
    @Transactional
    public void delete(int blogId) {
        log.info(">>> BlogService delete: {}", blogId);

        BlogEntity blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        blogRepository.delete(blog);
    }

    @Transactional(readOnly = true)
    public List<BlogResponseDTO> list() {
        log.info(">>> BlogService list");

        return blogRepository.findAll()
                .stream()
                .map(BlogResponseDTO::fromEntityWithoutComments)
                .toList();
    }

    @Transactional
    public BlogResponseDTO update(Integer blogId, BlogRequestDTO request) {
        log.info(">>> BlogService update: {}", blogId);

        BlogEntity blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        // setter를 통한 entity의 수정은 영속성 측면에서 부적합
        // entity 내에 update 함수를 선언하는 방식이 적합함
        blog.update(request.getTitle(), request.getContent());

        return BlogResponseDTO.fromEntityWithoutComments(blog);
    }

}
