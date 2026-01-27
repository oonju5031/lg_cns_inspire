package com.example.blog.user.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.blog.user.domain.dto.UserRequestDTO;
import com.example.blog.user.domain.dto.UserResponseDTO;

@Mapper
public interface UserMapper {
    int insertRow(UserRequestDTO request);
    UserResponseDTO selectRow(UserRequestDTO request);
}


