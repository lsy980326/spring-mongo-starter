package com.template.spring_mongodb.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * - 로그인 요청 데이터를 담는 DTO
 * : 사용자 아이디와 비밀번호를 전달하는 역할
 */

@Schema(
    name = "Login",
    description = "로그인"
)
public record LoginDto(
    @Schema(
        description = "사용자 아이디",
        example = "99999999"
    )
    String username,

    @Schema(
        description = "비밀번호",
        example = "ulvac2920!"
    )
    String password
) {
}
