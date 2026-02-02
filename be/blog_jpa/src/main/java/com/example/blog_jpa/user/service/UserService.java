package com.example.blog_jpa.user.service;

import com.example.blog_jpa.common.service.RefreshTokenService;
import com.example.blog_jpa.common.util.JwtProvider;
import com.example.blog_jpa.user.domain.dto.UserRequestDTO;
import com.example.blog_jpa.user.domain.dto.UserResponseDTO;
import com.example.blog_jpa.user.domain.entity.UserEntity;
import com.example.blog_jpa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO join(UserRequestDTO request) {
        log.info(">>>> UserService signup");
        return Optional.of(request)
                .filter(req -> !userRepository.existsById(req.getEmail()))
                .map(req -> userRepository.save(req.toEntity()))
                .map(UserResponseDTO::fromEntity)
                .orElseThrow(() -> new RuntimeException("사용자가 이미 존재합니다."));
    }

    public Map<String, Object> login(UserRequestDTO request) {
        log.info(">>>> UserService signIn");
        Map<String, Object> map = new HashMap<>();

        log.debug(">> 1. UserService 사용자 조회");
        UserEntity entity = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException(">>> 로그인 실패"));

        if (!passwordEncoder.matches(request.getPassword(), entity.getPassword())) {
            throw new RuntimeException("Incorrect Password.");
        }

        log.debug(">> 2. UserService 토큰 생성");
        // TODO

        log.debug(">> 3. UserService RT토큰을 Redis에 저장");
        // TODO

        return map;
    }

}