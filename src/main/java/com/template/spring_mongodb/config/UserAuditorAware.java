package com.template.spring_mongodb.config;

import com.template.spring_mongodb.domain.user.AuthenticationService;
import com.template.spring_mongodb.domain.user.CurrentUser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAuditorAware implements AuditorAware<Long>, DateTimeProvider {
    private final AuthenticationService authenticationService;

    @NonNull
    @Override
    public Optional<Long> getCurrentAuditor() {
        return authenticationService.getLoginUser()
            .map(CurrentUser::getId);
    }

    @NonNull
    @Override
    public Optional<TemporalAccessor> getNow() {
        return Optional.of(Instant.now());
    }
}
