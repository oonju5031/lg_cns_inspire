package com.example.blog.user.service;

import java.util.HashMap;
import java.util.Map;

import com.example.blog.common.service.RefreshTokenService;
import com.example.blog.common.token.JwtProvider;
import com.example.blog.user.domain.dto.UserResponseDTO;
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
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    public int join(UserRequestDTO request) {
        log.info(">>>> UserService signup");
        return userMapper.insertRow(request);
    }

    public Map<String, Object> login(UserRequestDTO request) {
        log.info(">>>> UserService signIn");
        Map<String, Object> map = new HashMap<>();

        log.debug(">> 1. UserService 사용자 조회");
        UserResponseDTO user = userMapper.selectRow(request);
        if (user == null) {
            throw new RuntimeException(">>> 로그인 실패");
        }

        log.debug(">> 2. UserService 토큰 생성");
        String at = jwtProvider.createAt(user.getEmail());
        String rt = jwtProvider.createRt(user.getEmail());

        log.debug(">> 3. UserService RT토큰을 Redis에 저장");
        refreshTokenService.saveToken(user.getEmail(), rt);

        map.put("response", user);
        map.put("access", at);
        map.put("refresh", rt);

        return map ;
    }

}
