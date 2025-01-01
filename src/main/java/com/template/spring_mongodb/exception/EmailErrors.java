package com.template.spring_mongodb.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailErrors {
    public static class FailSend extends AnasaException {
        private static final String MESSAGE = "error.email.fail-send";

        public FailSend() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.SERVICE_UNAVAILABLE;
        }
    }
}