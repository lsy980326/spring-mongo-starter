package com.template.spring_mongodb.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
@SuppressWarnings("java:S1948")
public abstract class AnasaException extends RuntimeException {
    private static final String PACKAGE_PREFIX = "com.template";

    private final StackTraceElement location;
    private final Object[] arguments;

    protected AnasaException(String message, Object... arguments) {
        super(message);
        this.arguments = arguments;

        this.location = Arrays.stream(Thread.currentThread().getStackTrace())
            .filter(e -> e.getClassName().startsWith(PACKAGE_PREFIX))
            .skip(2) // AnasaException, CustomException 제외하고 예외 발생 위치를 찾기 위함
            .findFirst()
            .orElse(null);
    }

    public abstract HttpStatus getStatus();

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public String getExceptionDetails() {
        return "location=" + this.location.toString() + ", arguments=" + Arrays.toString(this.arguments);
    }
}
