package com.example.blog.blog.repository;

import com.example.blog.blog.domain.dto.BlogRequestDTO;
import com.example.blog.blog.domain.dto.BlogResponseDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper {
    void insertRow(BlogRequestDTO request);
    BlogResponseDTO readRow(Integer blogId);
    int deleteRow(Integer blogId);
}
