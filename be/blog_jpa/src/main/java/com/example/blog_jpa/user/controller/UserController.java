package com.example.blog_jpa.user.controller;

import com.example.blog_jpa.user.domain.dto.UserRequestDTO;
import com.example.blog_jpa.user.domain.dto.UserResponseDTO;
import com.example.blog_jpa.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
@Tag(name = "User API" ,  description = "사용자 생성 및 로그인 관련 API 명세서")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @ApiResponses(
            {
                    @ApiResponse(responseCode="200" , description="데이터 입력 성공"),
                    @ApiResponse(responseCode="400" , description="잘못된 요청")
            }
    )
    @Operation(
            summary = "회원가입",
            description = "신규 회원을 가입한다(email, password, name)"
    )
    @PostMapping("/signup")
    public ResponseEntity<Void> join(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "사용자 가입 DTO",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UserRequestDTO.class)
                    ))
            @RequestBody UserRequestDTO request) {

        System.out.println(">>>> user ctrl path : /signup");
        System.out.println(">>>> params : "+ request);

        // 패스워드 해싱
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        UserResponseDTO response = userService.join(request);

        // if or Optional
        if(response != null) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @ApiResponses(
            {
                    @ApiResponse(responseCode="201" , description="데이터 입력 성공"),
                    @ApiResponse(responseCode="400" , description="잘못된 요청")
            }
    )
    @Operation(
            summary = "로그인",
            description = "인증된 사용자 로그인(email, password)"
    )


    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "사용자 로그인 DTO",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UserRequestDTO.class)
                    ))
            @RequestBody UserRequestDTO request) {

        System.out.println(">>>> user ctrl path : /signin");
        System.out.println(">>>> params : "+ request);

        Map<String, Object> map = userService.login(request);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + map.get("access"));
        headers.add("Refresh-Token", (String) map.get("refresh"));
        headers.add("Access-Control-Expose-Headers", "Authorization, Refresh-Token");
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body( (UserResponseDTO)(map.get("response")) ) ;
    }

}