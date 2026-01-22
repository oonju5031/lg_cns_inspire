package com.example.blog.blog.controller;

import com.example.blog.blog.domain.dto.BlogRequestDTO;
import com.example.blog.blog.domain.dto.BlogResponseDTO;
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

        int flag = blogService.write(request);

        if (flag != 0) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/read/{blogId}")
    public ResponseEntity<BlogResponseDTO> read(@PathVariable Integer blogId) {
        log.info(">>> BlogController read: {}", blogId);

        BlogResponseDTO response = blogService.read(blogId);

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity<Void> delete(@PathVariable Integer blogId) {
        log.info(">>> BlogController delete: {}", blogId);

        int flag = blogService.delete(blogId);

        if (flag != 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
