package com.example.blog_jpa.openai.controller;

import com.example.blog_jpa.openai.domain.ChatResponseDTO;
import com.example.blog_jpa.openai.domain.MessageRequestDTO;
import com.example.blog_jpa.openai.domain.MessageResponseDTO;
import com.example.blog_jpa.openai.domain.QuizResponseDTO;
import com.example.blog_jpa.openai.service.ChatService;
import com.example.blog_jpa.openai.service.MessageService;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/openai")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final MessageService messageService;

    @PostMapping("/chat")
    public ResponseEntity<ChatResponseDTO> chat(@RequestParam String weather, @RequestParam String location) throws IOException {
        log.info(">>> ChatController chat");

        chatService.recommend(weather, location);

        return ResponseEntity.ok().body(chatService.recommend(weather, location));
    }

    // 2세대 API
    @PostMapping("/ask")
    public ResponseEntity<MessageResponseDTO> ask(@RequestBody MessageRequestDTO requestDTO) {
        log.info(">>> ChatController ask: {}", requestDTO);

        messageService.ask(requestDTO);

        return ResponseEntity.ok().body(messageService.ask(requestDTO));
    }

    // 3세대 API
    @PostMapping("/generateMessage")
    public Mono<MessageResponseDTO> generateMessage(@RequestBody MessageRequestDTO requestDTO) {
        log.info(">>> ChatController generateMessage: {}", requestDTO);

        return messageService.generateMessage(requestDTO);
    }

    // 2세대 API
    @PostMapping("/quiz")
    public ResponseEntity<QuizResponseDTO> quiz(@RequestParam String subject) {
        log.info(">>> ChatController quiz");

        return ResponseEntity.ok().body(chatService.quiz(subject));
    }

    @PostMapping("/chatBot")
    public ChatResponse chatBot(@RequestBody ChatRequest request) {
        log.info(">>> ChatController chatBot");

        return ChatResponse
                .builder()
                .reply( chatService.chatBot(request.getMessage()) )
                .build() ;
    }

    @Builder
    @Getter
    @ToString
    public static class ChatRequest {
        private String message;
    }

    @Builder
    @Getter
    @ToString
    public static class ChatResponse {
        private String reply;
    }

}
