package com.template.spring_mongodb.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * - 현재 사용자 정보 클래스
 * : 서버에 로그인한 사용자의 정보를 담고 있는 객체로,
 *   사용자 ID(id), 사용자 이름(username), 이름(name), 이메일(email), 전화번호(phoneNumber) 등의 정보를 포함.
 */

@Getter
@Builder
@ToString
public class CurrentUser {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String phoneNumber;
//    private UserSettingDto setting;
}
