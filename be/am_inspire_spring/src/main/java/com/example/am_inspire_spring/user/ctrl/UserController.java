package com.example.am_inspire_spring.user.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.am_inspire_spring.user.domain.dto.UserRequestDTO;
import com.example.am_inspire_spring.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/test")
    @ResponseBody
    public String healthCheck() {
        return "ok";
    }

    @PostMapping("/join")
    public String join(@RequestBody UserRequestDTO request) {
        log.info(">>> UserController join");
        log.info(">>> params: " + request);

        userService.insert(request);

        return null;
    }
}
