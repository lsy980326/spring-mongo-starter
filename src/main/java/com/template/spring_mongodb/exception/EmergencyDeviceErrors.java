package com.template.spring_mongodb.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmergencyDeviceErrors {

    /**
     * - 404: 존재하지 않는 비상용 단말기입니다.
     */
    public static class EmergencyDeviceNotFountException extends AnasaException {
            private static final String MESSAGE = "error.emergencyDevice.required-values-not-found";

            public EmergencyDeviceNotFountException() {
                super(MESSAGE);
            }

            @Override
            public HttpStatus getStatus() {
                return HttpStatus.NOT_FOUND;
            }
    }

    /**
     * - 409: 이미 존재하는 비상용 단말기입니다.
     */
    public static class EmergencyDeviceExistsException extends AnasaException {
            private static final String MESSAGE = "error.emergencyDevice.required-values-exists-emergencyDevice";

            public EmergencyDeviceExistsException() {
                super(MESSAGE);
            }

            @Override
            public HttpStatus getStatus() {
                return HttpStatus.NOT_FOUND;
            }
    }

}