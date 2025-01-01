package com.template.spring_mongodb.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BusinessErrors {

    /**
     * - 400: 기본 사업장 정보를 찾을 수 없습니다.
     */
    public static class DefaultBusinessNotFoundException extends AnasaException {
        private static final String MESSAGE = "error.business.default-business-not-found";

        public DefaultBusinessNotFoundException() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.BAD_REQUEST;
        }
    }

    /**
     * - 400: 사업장 정보를 찾을 수 없습니다.
     */
    public static class BusinessNotExistsException extends AnasaException {
        private static final String MESSAGE = "error.business.required-values-not-exists-business";

        public BusinessNotExistsException() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.BAD_REQUEST;
        }
    }

    /**
     * - 404: 존재하지 않는 사업장입니다.
     */
    public static class BusinessNotFountException extends AnasaException {
            private static final String MESSAGE = "error.business.required-values-not-found";

            public BusinessNotFountException() {
                super(MESSAGE);
            }

            @Override
            public HttpStatus getStatus() {
                return HttpStatus.NOT_FOUND;
            }
    }

    /**
     * - 409: 이미 존재하는 사업장입니다.
     */
    public static class BusinessExistsException extends AnasaException {
            private static final String MESSAGE = "error.business.required-values-exists-business";

            public BusinessExistsException() {
                super(MESSAGE);
            }

            @Override
            public HttpStatus getStatus() {
                return HttpStatus.CONFLICT;
            }
        }
}