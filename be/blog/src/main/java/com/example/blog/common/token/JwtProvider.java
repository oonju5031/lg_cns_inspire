package com.example.blog.common.token;

import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    public String createAt(String email) {
        return "access-token-for-" + email;
    }

    public String createRt(String email) {
        return "refresh-token-for-" + email;
    }
}
