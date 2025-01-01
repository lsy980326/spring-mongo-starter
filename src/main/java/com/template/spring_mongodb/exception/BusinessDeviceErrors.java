package com.template.spring_mongodb.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BusinessDeviceErrors {

    /**
     * - 409: 이미 존재하는 사업장 단말기입니다.
     */
    public static class BusinessDeivceExistsException extends AnasaException {
            private static final String MESSAGE = "error.businessDevice.required-exists-businessDevice";

            public BusinessDeivceExistsException() {
                super(MESSAGE);
            }

            @Override
            public HttpStatus getStatus() {
                return HttpStatus.CONFLICT;
            }
        }

    /**
     * - 404: 해당 사업장에서 관리하지 않는 단말기 그룹입니다.
     */
    public static class BusinessDeivceNotExistsException extends AnasaException {
            private static final String MESSAGE = "error.businessDevice.required-not-exists-businessDevice";

            public BusinessDeivceNotExistsException() {
                super(MESSAGE);
            }

            @Override
            public HttpStatus getStatus() {
                return HttpStatus.NOT_FOUND;
            }
        }
}