package com.template.spring_mongodb.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.Objects;

@Getter
@Setter
public class ApiResponse<T> extends ResponseEntity<ApiResponseBody<T>> {
    private static final String MESSAGE_SUCCESS = "common.success";
    private static final String MESSAGE_CREATED = "common.created";
    private static final String MESSAGE_FAIL = "common.fail";
    private static final String MESSAGE_NOT_FOUND = "common.not-found";

    @Builder
    public ApiResponse(HttpHeaders headers, @NonNull HttpStatus status, T data, Message message, Map<String, Message> errors) {
        super(new ApiResponseBody<>(status.value(), data, message, errors), headers, status);
    }

    public static <T> ApiResponse<T> of(@NonNull HttpStatus status, T data, String message, Object... arguments) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ApiResponse<>(headers, status, data, new Message(message, arguments), null);
    }

    public static <T> ApiResponse<T> of(@NonNull HttpStatus status, T data, Message message) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ApiResponse<>(headers, status, data, message, null);
    }

    public static <T> ApiResponse<T> of(@NonNull HttpStatus status, T data, Message message, Map<String, Message> errors) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ApiResponse<>(headers, status, data, message, errors);
    }

    // 응답 성공에 대한 메소드
    public static <T> ApiResponse<T> success(T data, String message, Object... arguments) {
        return of(HttpStatus.OK, data, message, arguments);
    }

    public static <T> ApiResponse<T> success(T data) {
        return success(data, MESSAGE_SUCCESS);
    }

    public static <T> ApiResponse<T> success() {
        return success(null);
    }

    // 데이터 생성에 대한 응답 메소드
    public static <T> ApiResponse<T> created(T data, String message, Object... arguments) {
        return of(HttpStatus.CREATED, data, message, arguments);
    }

    public static <T> ApiResponse<T> created(T data) {
        return created(data, MESSAGE_CREATED);
    }

    // 권한이 없다는 응답 메소드
    public static <T> ApiResponse<T> unauthorized(String message, Object... arguments) {
        return of(HttpStatus.NOT_FOUND, null, message, arguments); // 403이 일반적이지만 리소스의 여부를 알 수 있으므로 404 응답
    }

    public static <T> ApiResponse<T> unauthorized() {
        return unauthorized(MESSAGE_NOT_FOUND);
    }

    // 범용적인 오류 응답 메소드
    public static <T> ApiResponse<T> error(@NonNull HttpStatus status, String message) {
        return of(status, null, message);
    }

    public static <T> ApiResponse<T> error(@NonNull HttpStatus status, Message message) {
        return of(status, null, message);
    }

    public static <T> ApiResponse<T> error(@NonNull HttpStatus status, String message, Map<String, Message> errors) {
        return of(status, null, new Message(message), errors);
    }

    public static <T> ApiResponse<T> error(@NonNull HttpStatus status, Message message, Map<String, Message> errors) {
        return of(status, null, message, errors);
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!super.equals(other)) {
            return false;
        }
        ApiResponse<?> otherEntity = (ApiResponse<?>) other;
        return ObjectUtils.nullSafeEquals(Objects.requireNonNull(this.getBody()).code(), Objects.requireNonNull(otherEntity.getBody()).code());
    }

    @Override
    public int hashCode() {
        return (29 * super.hashCode() + ObjectUtils.nullSafeHashCode(Objects.requireNonNull(this.getBody()).code()));
    }
}
