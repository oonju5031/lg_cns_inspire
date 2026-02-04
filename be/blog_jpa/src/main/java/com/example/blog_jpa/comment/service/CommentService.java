package com.example.blog_jpa.comment.service;

import com.example.blog_jpa.blog.domain.entity.BlogEntity;
import com.example.blog_jpa.blog.repository.BlogRepository;
import com.example.blog_jpa.comment.domain.dto.CommentRequestDTO;
import com.example.blog_jpa.comment.domain.dto.CommentResponseDTO;
import com.example.blog_jpa.comment.domain.entity.CommentEntity;
import com.example.blog_jpa.comment.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    /*
    데이터베이스 작업의 논리적인 단위로 트랜잭션처리가 필요
    commit, rollback
    */
    @Transactional
    public List<CommentResponseDTO> write(CommentRequestDTO request) {
        log.info(">>> CommentService write");

        List<CommentResponseDTO> list = null ;
        BlogEntity blog =
                blogRepository.findById(request.getBlogId())
                        .orElseThrow(() -> new RuntimeException("Blog not found: " + request.getBlogId()));

        commentRepository.save(request.toEntity(blog));

        return commentRepository.findByBlog_BlogId(request.getBlogId())
                .stream()
                .map(CommentResponseDTO::fromEntity)
                .toList();
    }

    @Transactional
    public void delete(Integer commentId) {
        System.out.println(">>>> blog/comment service delete");

        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글 없음: " + commentId));

        commentRepository.delete(comment);
    }

    private String getAuthEmail() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }

}