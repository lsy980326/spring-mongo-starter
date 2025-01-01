package com.template.spring_mongodb.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "Token Refresh",
    description = "토큰 갱신"
)
public record RefreshTokenDto(
    String refreshToken
) {
}
