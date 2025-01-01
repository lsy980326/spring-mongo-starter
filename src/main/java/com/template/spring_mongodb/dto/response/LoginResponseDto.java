package com.template.spring_mongodb.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * - LoginResponseDto 클래스
 * : 로그인 응답 객체를 정의합니다.
 */
@Schema(description = "로그인 응답 객체")
public record LoginResponseDto(
    @Schema(description = "토큰 정보", implementation = TokenDto.class)
    TokenDto token,

    @Schema(description = "사용자 정보", implementation = UserInformationDto.class)
    UserInformationDto userInformation
) {
    /**
     * - TokenDto 클래스
     * : 액세스 토큰 및 리프레시 토큰 정보를 정의합니다.
     */
    @Schema(description = "토큰 정보")
    public record TokenDto(
        @Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String accessToken,

        @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzI1NiJ9...")
        String refreshToken,

        @Schema(description = "토큰 유형", example = "Bearer")
        String tokenType,

        @Schema(description = "토큰 만료 시간 (초 단위)", example = "3600")
        String expiresIn
    ) {}

    /**
     * - UserInformationDto 클래스
     * : 사용자 정보를 정의합니다.
     */
    @Schema(description = "사용자 정보")
    public record UserInformationDto(
        @Schema(description = "관리자 ID", example = "1")
        Long adminId,

        @Schema(description = "관리자 이름", example = "John Doe")
        String adminName,

        @Schema(description = "관리자 부서", example = "IT Department")
        String adminDepartment
    ) {}
}
