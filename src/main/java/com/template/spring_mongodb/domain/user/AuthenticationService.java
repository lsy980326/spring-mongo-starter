package com.template.spring_mongodb.domain.user;

import com.template.spring_mongodb.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserMapper userMapper;

    /**
     * 스프링 시큐리티를 통해 로그인한 사용자의 기본 정보를 가져온다.
     */
    public Optional<CurrentUser> getLoginUser() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return switch (principal) {
            case User user -> Optional.ofNullable(userMapper.of(user));
            default -> Optional.empty();
        };
    }

    /**
     * 로그인한 사용자의 키를 가져와 캐시의 키로 사용한다. 로그인한 사용자가 없으면 0을 반환한다.
     */
    public Long getLoginUserCacheKey() {
        return getLoginUser()
            .map(CurrentUser::getId)
            .orElse(0L);
    }

    /**
     * 로그인한 사용자의 상세정보를 가져온다.
     */
    @Cacheable(value = "currentUser", key = "#root.target.getLoginUserCacheKey()")
    public CurrentUser getCurrentUser() {
        return getLoginUser()
            .orElse(null);
    }
}
