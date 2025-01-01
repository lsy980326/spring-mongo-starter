package com.template.spring_mongodb.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeviceGroupErrors {

    /**
     * - 404: 존재하지 않는 그룹입니다.
     */
    public static class DeviceGroupNotFountException extends AnasaException {
            private static final String MESSAGE = "error.deviceGroup.required-values-not-found";

            public DeviceGroupNotFountException() {
                super(MESSAGE);
            }

            @Override
            public HttpStatus getStatus() {
                return HttpStatus.NOT_FOUND;
            }
    }

}