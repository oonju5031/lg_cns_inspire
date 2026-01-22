package com.example.blog.blog.controller;

import com.example.blog.blog.domain.dto.BlogRequestDTO;
import com.example.blog.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/write")
    public ResponseEntity<Void> write(@RequestBody BlogRequestDTO request) {
        log.info(">>> BlogController write");

        blogService.write(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
