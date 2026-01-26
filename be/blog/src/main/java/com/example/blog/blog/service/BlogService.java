package com.example.blog.blog.service;

import com.example.blog.blog.domain.dto.BlogRequestDTO;
import com.example.blog.blog.domain.dto.BlogResponseDTO;
import com.example.blog.blog.repository.BlogMapper;
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

    @Transactional
    public int write(BlogRequestDTO requestDTO) {
        log.info(">>> BlogService write");

        return blogMapper.insertRow(requestDTO);
    }

    @Transactional(readOnly = true)
    public BlogResponseDTO read(int blogId) {
        log.info(">>> BlogService read: {}", blogId);

        return blogMapper.readRow(blogId);
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
