package com.template.spring_mongodb.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "User",
    description = "사용자"
)
public record UserDto(
    @Schema(
        description = "키",
        example = "0"
    )
    Long id,

    @Schema(
        description = "이메일",
        example = "template@template.co.kr"
    )
    String email,

    @Schema(
        description = "아이디",
        example = "template@template.co.kr"
    )
    String username
) {

}
