package com.template.spring_mongodb.exception;

import com.template.spring_mongodb.common.ApiResponse;
import com.template.spring_mongodb.common.Message;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.metadata.ConstraintDescriptor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@AllArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String UNKNOWN_ERROR = "error.common.unknown-error";
    private static final String BAD_REQUEST = "error.common.bad-request";
    private static final String NOT_FOUND = "error.common.not-found";

    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleException(Exception ex) {
        log.error("An error occurred", ex);
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, UNKNOWN_ERROR);
    }

    @ExceptionHandler(AnasaException.class)
    public ApiResponse<Void> handleAnasaException(AnasaException ex) {
        String message = messageSource.getMessage(ex.getMessage(), ex.getArguments(), ex.getMessage(), Locale.KOREA);
//        var user = userService.getCurrentUser();
//        if (user != null) {
//            log.error("AnasaException occurred by User '{}'[{}] { message='{}', {} }]", user.getUsername(), user.getId(), message, ex.getExceptionDetails(), ex);
//        } else {
//            log.error("AnasaException occurred [message={}, {}]", message, ex.getExceptionDetails(), ex);
//        }
        return ApiResponse.error(ex.getStatus(), new Message(ex.getMessage(), ex.getArguments()));
    }


    @ExceptionHandler(NoResourceFoundException.class)
    public ApiResponse<Void> handleNoResourceFoundException(NoResourceFoundException ex) {
        log.error("No resource found", ex);
        return ApiResponse.error(HttpStatus.NOT_FOUND, NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ApiResponse<Void> handleNoSuchElementException(NoSuchElementException ex) {
        log.error("No such element", ex);
        return ApiResponse.error(HttpStatus.NOT_FOUND, NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResponse<Void> handleNoResourceFoundException(HttpRequestMethodNotSupportedException ex) {
        log.error("Http request method not supported", ex);
        return ApiResponse.error(HttpStatus.NOT_FOUND, NOT_FOUND);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ApiResponse<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        Map<String, Message> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach(error -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            if (errorMessage != null) errors.put(fieldName, new Message(errorMessage));
//        });
//        log.error("Invalid request [ {} ]", errors);
//        return ApiResponse.error(HttpStatus.BAD_REQUEST, BAD_REQUEST, errors);
//    }

    // 유효성 검사 실패를 커스텀 예외로 변환
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Void> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // 모든 에러 메시지 저장 및 value 값 추출
       Map<String, String> errors = new HashMap<>();
       String firstFieldName = null;
       String fieldMessage = null;
       Object minValue = null;

       for (FieldError error : ex.getBindingResult().getFieldErrors()) {
           String fieldName = error.getField();
           String errorMessage = error.getDefaultMessage();

           // ConstraintDescriptor에서 value 값 추출
           if (error.unwrap(ConstraintViolation.class) != null) {
               ConstraintViolation<?> violation = error.unwrap(ConstraintViolation.class);
               ConstraintDescriptor<?> descriptor = violation.getConstraintDescriptor();
               if (descriptor.getAttributes().containsKey("value")) {
                   minValue = descriptor.getAttributes().get("value");
               }
           }

           errors.put(fieldName, errorMessage);

           // 첫 번째 필드 저장
           if (firstFieldName == null) {
               firstFieldName = fieldName;
               fieldMessage = errorMessage;
           }
       }

       System.out.println("첫 번째 필드 이름: " + firstFieldName);
       System.out.println("첫 번째 에러 메시지: " + fieldMessage);
       System.out.println("value 값: " + minValue);

       if(minValue != null) {
           // ApiResponse에 첫 번째 에러 메시지와 value 값 반환
           return ApiResponse.error(
                   HttpStatus.UNPROCESSABLE_ENTITY,
                   new Message(fieldMessage, minValue)
           );
       }

        // ApiResponse에 첫 번째 에러 메시지 반환
        return ApiResponse.error(
            HttpStatus.UNPROCESSABLE_ENTITY,
            new Message(fieldMessage, firstFieldName)
        );
    }

    // 잘못된 요청 데이터 형식
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponse<Void> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("Invalid json", ex);
        return ApiResponse.error(HttpStatus.UNPROCESSABLE_ENTITY, "error.validation.not-readable");
    }

    // preAuthorize 실패 (토큰인증, 전부 허용만 처리함)
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ApiResponse<Void> handleAuthorizationDeniedException(AuthorizationDeniedException ex) {
        log.error("Invalid json", ex);
        return ApiResponse.error(HttpStatus.UNAUTHORIZED, "error.security.access-denied");
    }

}
