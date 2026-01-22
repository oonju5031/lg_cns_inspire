package com.example.am_inspire_spring.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.am_inspire_spring.user.dao.UserMapper;
import com.example.am_inspire_spring.user.domain.dto.UserRequestDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void insert(UserRequestDTO request) {
        log.info(">>> UserService insertRow");

        userMapper.insertRow(request);
    }
}
