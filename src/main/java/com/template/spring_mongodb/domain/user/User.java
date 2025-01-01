package com.template.spring_mongodb.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * - 유저 엔티티 클래스
 * : 사용자 정보와 관련된 속성 및 비즈니스 로직을 정의하며, Spring Security의 UserDetails 인터페이스를 구현하여
 *   인증 및 권한 관련 메서드를 제공합니다. 사용자 그룹, 알림 설정, 로그인 실패 및 성공 횟수 관리 등의 기능 포함.
 */


@Getter
@Entity
@ToString
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {
    @Id
    @Column(
            name = "Mem_Key"
    )
    private Long id;

    @Column(
            name = "User_Name"
    )
    private String username;

    @Column(
            name = "Mem_Name"
    )
    private String name;

    @Column(
            name = "pw"
        )
    private String password;

    @Column(
            name = "Mem_Email"
        )
    private String email;

    @Column(
        name = "Mem_Dep_Key"
    )
    private String memberDepKey;

    @Column(
            name = "Mem_Dep_Name"
    )
    private String memberDepName;

    @Column(
        name = "use_yn"
    )
    private String useYn;

    public User(Long id, String username, String name, String password, String email, String memberDepKey, String memberDepName, String useYn) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.memberDepKey = memberDepKey;
        this.memberDepName = memberDepName;
        this.useYn = useYn;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void loginSuccess() {
    }

    public void loginFail() {
    }
}