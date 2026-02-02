package com.example.blog_jpa.common.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;

@Slf4j
// @Component  // JWT version
public class JwtFilter implements Filter {

    @Value("${jwt.secret}")
    private String secret;
    private Key key;

    @PostConstruct
    private void init() {
        log.debug(">>> Provider jwt secret: {}", secret);
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    private final AntPathMatcher matcher = new AntPathMatcher();

    // 토큰 없이도 접근 가능한 엔드포인트 목록(화이트리스트)
    private static final List<String> WHITE_LIST_PATH = List.of(
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/users/**"
    );

    // 화이트리스트에 포함된 엔드포인트인지 판정
    public boolean isPath(String path) {
        return WHITE_LIST_PATH.stream()
                .anyMatch(p -> matcher.match(p, path));
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        log.debug(">>> JwtFilter doFilter");

        HttpServletRequest hReq = (HttpServletRequest) request;
        HttpServletResponse hRes = (HttpServletResponse) response;

        String endPoint = hReq.getRequestURI();
        String method = hReq.getMethod();
        log.debug(">>> JwtFilter User EndPoint: {}", hReq.getRequestURI());
        log.debug(">>> JwtFilter Request Method: {}",method);

        if ("OPTIONS".equalsIgnoreCase(hReq.getMethod())) {
            hRes.setStatus(HttpServletResponse.SC_OK);
            hRes.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
            hRes.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            hRes.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
            hRes.setHeader("Access-Control-Allow-Headers", "true");

            chain.doFilter(request, response);
            return;
        }

        if (isPath(endPoint)) {
            log.debug(">>> JwtFilter {}는 토큰 없이 필터 통과", endPoint);
            chain.doFilter(request, response);
            return;
        }

        // header, token 검증을 해서
        // 통과
        String authHeader = hReq.getHeader("Authorization");
        log.debug(">>> JwtFilter authHeader: {}", authHeader);
        //  또는 reject
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.debug(">>> JwtFilter Not Authorized.");
            return;
        }

        String token = authHeader.substring(7);
        log.debug(">>> JwtFilter token: {}", token);
        log.debug(">> JwtFilter Validation Check");
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            log.debug(">>> JwtFilter token validation success, move to controller");
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.info(">>> JwtFilter Token Validation Failed.");
        }

    }
}
