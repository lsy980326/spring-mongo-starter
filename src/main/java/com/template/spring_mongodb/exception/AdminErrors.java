package com.template.spring_mongodb.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * - 유저 오류 정의 클래스
 * : 사용자 관련 예외 상황을 정의하여, 다양한 오류 상황에서 특정한 메시지와 HTTP 상태 코드를 반환하는 역할
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminErrors {

    /**
     * - 400: 외부번호에 해당하는 사용자가 없습니다.
     */
    public static class RequiredValuesNotExists extends AnasaException {
        private static final String MESSAGE = "error.admin.required-values-ext-admin-not-found";

        public RequiredValuesNotExists() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.BAD_REQUEST;
        }
    }

    /**
     * - 409: 이미 존재하는 관리자입니다.
     */
    public static class RequiredValuesExistsAdmin  extends AnasaException {
        private static final String MESSAGE = "error.admin.required-values-exists-admin";
        public RequiredValuesExistsAdmin() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.CONFLICT;
        }
    }

    /**
     * - 400: 존재하지 않는 관리자입니다.
     */
    public static class RequiredValuesNotExistsAdmin  extends AnasaException {
        private static final String MESSAGE = "error.admin.required-values-not-exists-admin";
        public RequiredValuesNotExistsAdmin() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.BAD_REQUEST;
        }
    }


    /**
     * - 403: 관리자 권한이 없습니다.
     */
    public static class RequiredValuesNotFORBIDDEN  extends AnasaException {
        private static final String MESSAGE = "error.admin.required-values-not-forbidden";
        public RequiredValuesNotFORBIDDEN() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.FORBIDDEN;
        }
    }

    /**
     * - 404: 관리자 정보를 찾을 수 없습니다.
     */
    public static class NotFound extends AnasaException {
        private static final String MESSAGE = "error.admin.required-values-not_found-admin";

        public NotFound() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.NOT_FOUND;
        }
    }
}

