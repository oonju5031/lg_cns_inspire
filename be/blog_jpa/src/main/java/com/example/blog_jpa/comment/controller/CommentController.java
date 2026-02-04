package com.example.blog_jpa.comment.controller;

import com.example.blog_jpa.comment.domain.dto.CommentRequestDTO;
import com.example.blog_jpa.comment.domain.dto.CommentResponseDTO;
import com.example.blog_jpa.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs/comments")
@RequiredArgsConstructor
@Tag(name = "Blog Comments API" ,  description = "Blog 댓글관련 API 명세서")
public class CommentController {

    private final CommentService commentService ;

    @ApiResponses(
            {
                    @ApiResponse(responseCode="201" , description="데이터 입력 성공"),
                    @ApiResponse(responseCode="400" , description="잘못된 요청")
            }
    )
    @Operation(
            summary = "특정블로그에 댓글 작성",
            description = "댓글을 신규로 작성한다(content, blogId)"
    )
    @PostMapping("/write")
    public ResponseEntity<List<CommentResponseDTO>> write(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "블로그댓글 작성 요청 DTO",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CommentRequestDTO.class)
                    ))
            @RequestBody CommentRequestDTO request) {

        System.out.println(">>>> blog / comment ctrl path : /write");
        System.out.println(">>>> params : "+ request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentService.write(request));

    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Object> delete(@PathVariable Integer commentId) {
        System.out.println(">>>> blog / comment  ctrl path : /delete");
        System.out.println(">>>> params : "+ commentId);

        commentService.delete(commentId);
        return ResponseEntity.noContent().build();
    }

}