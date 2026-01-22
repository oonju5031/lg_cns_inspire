package com.example.am_inspire_spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.am_inspire_spring.user.dao.UserMapper;
import com.example.am_inspire_spring.user.domain.dto.UserRequestDTO;
import com.example.am_inspire_spring.user.service.UserService;

@SpringBootTest
public class MyBatisAppTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void userInsert() {
        UserRequestDTO request = UserRequestDTO.builder()
                                            .email("test1234@gmail.com")
                                            .password("testpwd1234")
                                            .name("테스트")
                                            .build();
        
        System.out.println("[TEST] >>> mapper address: " + userMapper);
        System.out.println("[TEST] >>> service address: " + userService);

        userService.insert(request);
    }
}
