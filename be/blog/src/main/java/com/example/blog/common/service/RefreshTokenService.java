package com.example.blog.common.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RedisTemplate<String, Object> redisTemplate;

    // TTL (Time-to-Limit): ms 단위 -> 임의로 일주일로 설정
    private static final long REFRESH_TOKEN_TTL = 60 * 60 * 24 * 7;

    public void saveToken(String email, String refreshToken) {
        log.info(">>> RefreshTokenService saveToken");
        redisTemplate.opsForValue().set("RT:" + email, refreshToken, REFRESH_TOKEN_TTL);
        // redisTemplate.opsForValue().set("RT:" + email, refreshToken, REFRESH_TOKEN_TTL, TimeUnit.SECONDS);  // 이 경우 ms 대신 s 단위로 사용
    }

    public void deleteToken(String email) {
        log.info(">>> RefreshTokenService deleteToken");
        redisTemplate.delete("RT:" + email);
    }

    // 확인용 코드 -> 기능상 필요하진 않음
    public String findByEmail(String email) {
        log.info(">>> RefreshTokenService findByEmail: {}", email);
        return (String) redisTemplate.opsForValue().get("RT:" + email);
    }
}
