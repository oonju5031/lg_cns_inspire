package com.example.blog.blog.controller;

import com.example.blog.blog.domain.dto.BlogRequestDTO;
import com.example.blog.blog.domain.dto.BlogResponseDTO;
import com.example.blog.blog.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/blogs")
@RestController
@RequiredArgsConstructor
@Tag(name = "Blog API", description = "Blog API 명세서")
public class BlogController {

    private final BlogService blogService;

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "데이터 입력 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @Operation(
            summary = "블로그 글 작성",
            description = "신규 게시글 작성(title not null, email fk)"
    )
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

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = BlogResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "존재하지 않는 글"
            )
    })
    @Operation(
            summary = "블로그 글 조회",
            description = "아이디를 이용한 단일 게시글 조회"
    )
    @GetMapping("/read/{blogId}")
    public ResponseEntity<BlogResponseDTO> read(@Parameter(description = "블로그 ID", example = "1") @PathVariable Integer blogId) {
        log.info(">>> BlogController read: {}", blogId);

        BlogResponseDTO response = blogService.read(blogId);

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "404" , description = "존재하지 않는 글")
    })
    @Operation(
            summary = "블로그 글 삭제",
            description = "아이디를 이용한 단일 게시글 삭제"
    )
    @Parameters({
            @Parameter(name = "blogId", description = "블로그 ID", example = "1")
    })
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

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = BlogResponseDTO.class)
                            )
                    )
            ),

            @ApiResponse(
                    responseCode = "204",
                    description = "데이터가 존재하지 않음"
            )
    })
    @Operation(
            summary = "블로그 글 목록 조회",
            description = "전체 게시글 조회"
    )
    @GetMapping("/list")
    public ResponseEntity<List<BlogResponseDTO>> list() {
        log.info(">>> BlogController list");

        List<BlogResponseDTO> list = blogService.list();
        if (!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "404" , description = "존재하지 않는 글")
    })
    @Operation(
            summary = "블로그 글 수정",
            description = "아이디를 이용한 단일 게시글 수정"
    )
    @Parameters({
            @Parameter(name = "blogId", description = "블로그 ID", example = "1")
    })
    @PutMapping("/update/{blogId}")
    public ResponseEntity<Void> update(@PathVariable Integer blogId, @RequestBody BlogRequestDTO request) {
        log.info(">>> BlogController update: {}", blogId);

        int flag = blogService.update(blogId, request);

        if (flag != 0) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
