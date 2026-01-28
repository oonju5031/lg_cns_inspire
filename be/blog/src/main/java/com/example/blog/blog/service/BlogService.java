package com.example.blog.blog.service;

import com.example.blog.blog.domain.dto.BlogRequestDTO;
import com.example.blog.blog.domain.dto.BlogResponseDTO;
import com.example.blog.blog.repository.BlogMapper;
import com.example.blog.comment.domain.dto.CommentResponseDTO;
import com.example.blog.comment.repository.CommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BlogService {

    private final BlogMapper blogMapper;
    private final CommentMapper commentMapper;

    @Transactional
    public int write(BlogRequestDTO requestDTO) {
        log.info(">>> BlogService write");

        return blogMapper.insertRow(requestDTO);
    }

    @Transactional(readOnly = true)
    public BlogResponseDTO read(int blogId) {
        log.info(">>> BlogService read: {}", blogId);

        BlogResponseDTO blog = blogMapper.readRow(blogId);
        if (blog == null) {
            throw new RuntimeException("게시글 없음");
        }

        List<CommentResponseDTO> comments = commentMapper.selectRow(blog.getBlogId());
        blog.setComments(comments);

        return blog;
    }

    @Transactional
    public int delete(int blogId) {
        log.info(">>> BlogService delete: {}", blogId);

        return blogMapper.deleteRow(blogId);
    }

    @Transactional(readOnly = true)
    public List<BlogResponseDTO> list() {
        log.info(">>> BlogService list");

        return blogMapper.selectRow();
    }

    @Transactional
    public int update(Integer blogId, BlogRequestDTO request) {
        log.info(">>> BlogService update: {}", blogId);

        Map<String, Object> map = new HashMap<>();
        map.put("blogId", blogId);
        map.put("title", request.getTitle());
        map.put("content", request.getContent());

        return blogMapper.updateRow(map);
    }

}
