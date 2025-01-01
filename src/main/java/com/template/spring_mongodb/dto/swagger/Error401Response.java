package com.template.spring_mongodb.dto.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@Schema(description = "API 응답 데이터")
public class Error401Response {
    @Schema(description = "메시지", example = "사용자 인증에 실패했습니다.")
    private String message;
}
