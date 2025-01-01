package com.template.spring_mongodb.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeviceErrors {

    /**
     * - 404: 존재하지 않는 단말기입니다.
     */
    public static class DeviceNotFountException extends AnasaException {
            private static final String MESSAGE = "error.device.required-values-not-found";

            public DeviceNotFountException() {
                super(MESSAGE);
            }

            @Override
            public HttpStatus getStatus() {
                return HttpStatus.NOT_FOUND;
            }
    }

}