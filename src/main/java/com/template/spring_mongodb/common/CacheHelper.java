package com.template.spring_mongodb.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CacheHelper {
    /**
     * 사용자 설정 캐시 삭제
     */
    @CacheEvict(value = "userSetting", key = "#userId")
    public void evictUserSettingCache(Long userId) {
        log.info("evict user-setting cache {}", userId);
    }
}
