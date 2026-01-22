package com.example.blog.blog.service;

import com.example.blog.blog.domain.dto.BlogRequestDTO;
import com.example.blog.blog.domain.dto.BlogResponseDTO;
import com.example.blog.blog.repository.BlogMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogMapper blogMapper;

    public int write(BlogRequestDTO requestDTO) {
        log.info(">>> BlogService write");

        return blogMapper.insertRow(requestDTO);
    }

    public BlogResponseDTO read(int blogId) {
        log.info(">>> BlogService read: {}", blogId);

        return blogMapper.readRow(blogId);
    }

    public int delete(int blogId) {
        log.info(">>> BlogService delete: {}", blogId);

        return blogMapper.deleteRow(blogId);
    }

}
