package com.template.spring_mongodb.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
/**
 * - 유저 오류 정의 클래스
 * : 사용자 관련 예외 상황을 정의하여, 다양한 오류 상황에서 특정한 메시지와 HTTP 상태 코드를 반환하는 역할
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserErrors {
    /**
     * 오류: Keycloak에 등록되지 않은 사용자
     */
    public static class Unregistered extends AnasaException {
        private static final String MESSAGE = "error.user.unregistered";

        public Unregistered() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.UNAUTHORIZED;
        }
    }

    /**
     * 오류: 내부 데이터베이스에 존재하지 않는 사용자 (Keycloak에는 가입 완료)
     */
    public static class NotExists extends AnasaException {
        private static final String MESSAGE = "error.user.not-exists";

        public NotExists() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.SERVICE_UNAVAILABLE;
        }
    }

    /**
     * 오류: 이미 내부 데이터베이스에 존재하는 사용자 (Keycloak Id 중복)
     */
    public static class AlreadyExists extends AnasaException {
        private static final String MESSAGE = "error.user.already-exists";

        public AlreadyExists() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.CONFLICT;
        }
    }

    public static class BadCredentials extends AnasaException {
        private static final String MESSAGE = "error.user.bad-credentials";

        public BadCredentials() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.UNAUTHORIZED;
        }
    }

    public static class PasswordSameAsCurrent extends AnasaException {
        private static final String MESSAGE = "error.user.password-same-as-current";

        public PasswordSameAsCurrent() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.BAD_REQUEST;
        }
    }

    public static class InvalidToken extends AnasaException {
        private static final String MESSAGE = "error.user.invalid-token";

        public InvalidToken() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.UNAUTHORIZED;
        }
    }

    public static class InvalidRefreshToken extends AnasaException {
        private static final String MESSAGE = "error.user.invalid-token";

        public InvalidRefreshToken() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.FORBIDDEN;
        }
    }

    /**
     * 오류: 로그인되지 않은 상태로 요청 시
     */
    public static class RequiredLogin extends AnasaException {
        private static final String MESSAGE = "error.user.required-login";

        public RequiredLogin() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.UNAUTHORIZED;
        }
    }

    /**
     * 오류: 유저 정보 대조 시, 유저 정보가 일치하지 않을 경우
     */
    public static class NotMatch extends AnasaException {
        private static final String MESSAGE = "error.user.not-match";

        public NotMatch() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.BAD_REQUEST;
        }
    }

    public static class OnlyNumbers extends AnasaException {
        private static final String MESSAGE = "error.user.only-numbers";

        public OnlyNumbers() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.BAD_REQUEST;
        }
    }

    public static class LowerCaseAndNumbers extends AnasaException {
        private static final String MESSAGE = "error.user.lowercase-and-numbers";

        public LowerCaseAndNumbers() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.BAD_REQUEST;
        }
    }

    public static class IdLength extends AnasaException {
        private static final String MESSAGE = "error.user.id-length";

        public IdLength() {
            super(MESSAGE);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.BAD_REQUEST;
        }
    }

    public static class RequiredValuesNotFilled extends AnasaException {
        private static final String MESSAGE = "error.user.required-values-not-filled";

        public RequiredValuesNotFilled(String info) {
            super(MESSAGE, info);
        }

        @Override
        public HttpStatus getStatus() {
            return HttpStatus.BAD_REQUEST;
        }
    }
}