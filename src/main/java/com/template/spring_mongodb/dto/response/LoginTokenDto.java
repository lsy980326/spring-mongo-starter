package com.template.spring_mongodb.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * - 로그인 토큰 DTO
 * : 로그인 성공 시 사용자에게 반환되는 데이터 전송 객체로,
 *   사용자 아이디(username), 액세스 토큰(accessToken), 리프레시 토큰(refreshToken),
 *   그리고 토큰 유형(tokenType)을 포함.
 *   기본 토큰 유형은 "Bearer"로 설정됨.
 */


@Schema(
    name = "Login Token",
    description = "로그인 토큰"
)
public record LoginTokenDto(
    @Schema(
        description = "액세스 토큰",
        example = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidHlwZSI6IkFDQ0VTUyIsInN1YiI6ImFuYXNhQGFuYXNhLmNvLmtyIiwiaWF0IjoxNzE5NTU0NzE0LCJleHAiOjE3MTk1NTgzMTR9.kexDePPJvYKTCO1hzAt42AIJG_K5zJNI0X0ldCbRI6Q"
    )
    String accessToken,

    @Schema(
        description = "리프레시 토큰",
        example = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidHlwZSI6IlJFRlJFU0giLCJzdWIiOiJhbmFzYUBhbmFzYS5jby5rciIsImlhdCI6MTcxOTU1NDcxNCwiZXhwIjoxNzIwMTU5NTE0fQ.B4vj1yFbD3X3rZLw6UDtayJA1Hz8vaF7mRTbLbS3vWo"
    )
    String refreshToken,
    @Schema(
        description = "만료일자",
        example = "1732267136000"
    )
    String expiresIn,
    @Schema(
        description = "토큰 유형 - Bearer 고정",
        example = "Bearer"
    )
    String TokenType
) {
    public LoginTokenDto(String accessToken, String refreshToken, String expiresIn) {
        this(accessToken, refreshToken, expiresIn,"Bearer");
    }
}
