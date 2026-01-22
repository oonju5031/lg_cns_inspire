package com.example.am_inspire_spring.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.am_inspire_spring.user.domain.dto.UserRequestDTO;

@Mapper
public interface UserMapper {
    public void insertRow(UserRequestDTO request);
}
