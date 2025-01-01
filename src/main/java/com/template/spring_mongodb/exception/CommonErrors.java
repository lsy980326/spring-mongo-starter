package com.template.spring_mongodb.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonErrors {
    public static class NotFound extends AnasaException {
        private static final String MESSAGE = "error.common.not-found";

        public NotFound() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.NOT_FOUND;
        }
    }

    public static class NoPermission extends AnasaException {
        private static final String MESSAGE = "error.common.no-permission";

        public NoPermission() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.FORBIDDEN;
        }
    }
}
