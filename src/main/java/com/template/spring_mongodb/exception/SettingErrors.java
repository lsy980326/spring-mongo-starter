package com.template.spring_mongodb.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SettingErrors {
    public static class AlreadyExists extends AnasaException {
        private static final String MESSAGE = "error.setting.already-exists";

        public AlreadyExists() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.CONFLICT;
        }
    }

    /**
     * - 400: 환경설정 정보를 찾을 수 없습니다.
     */
    public static class NotFound extends AnasaException {
        private static final String MESSAGE = "error.setting.not-found";

        public NotFound() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.BAD_REQUEST;
        }
    }
}