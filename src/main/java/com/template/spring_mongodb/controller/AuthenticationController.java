package com.template.spring_mongodb.controller;

import com.template.spring_mongodb.dto.request.*;
import com.template.spring_mongodb.dto.response.LoginResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * - 인증 관련 컨트롤러
 * : 로그인, JWT 처리, 회원 가입
 */

@Tag(name = "인증")
@RequestMapping("/api/v1/auth/") // 기본 경로 설정
@Slf4j
@RestController
@AllArgsConstructor
public class AuthenticationController {

    @Operation(
        summary = "로그인"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공"),
        })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
//        var result = userService.login(loginDto);
//        return ResponseEntity.ok(result);
        return null;
    }

    @Operation(
        summary = "토큰 갱신"
    )
    @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "요청 성공"),
            })
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
//        var result = userService.refreshToken(refreshTokenDto.refreshToken());
//        return ResponseEntity.ok(result);
        return null;
    }


    @GetMapping("/test")
    public String test(@Param("test") String test) {
        return test;
    }


}