package com.example.blog.blog.repository;

import java.util.List;
import java.util.Map;

import com.example.blog.blog.domain.dto.BlogRequestDTO;
import com.example.blog.blog.domain.dto.BlogResponseDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper {
    int insertRow(BlogRequestDTO request);
    BlogResponseDTO readRow(Integer blogId);
    int deleteRow(Integer blogId);
    List<BlogResponseDTO> selectRow();
    int updateRow(Map<String, Object> request);
}