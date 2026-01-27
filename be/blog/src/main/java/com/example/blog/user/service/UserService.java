package com.example.blog.user.service;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.example.blog.user.repository.UserMapper;
import com.example.blog.user.domain.dto.UserRequestDTO;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper ; 

    public int join(UserRequestDTO request) {
        log.info(">>>> UserService signup");
        return userMapper.insertRow(request);
    }

    public Map<String, Object> login(UserRequestDTO request) {
        log.info(">>>> UserService signIn");
        Map<String, Object> map = new HashMap<>();

        map.put("response", userMapper.selectRow(request));
        map.put("access", "jwt-access-token");
        map.put("refresh", "jwt-refresh-token");

        return map ;
    }

}
