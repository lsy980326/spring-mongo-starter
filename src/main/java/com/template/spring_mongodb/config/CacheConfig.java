package com.template.spring_mongodb.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfig {
    private static final int TIME_1MIN = 60;
    private static final int MEMORY_10KB = 10 * 1024;

    @Bean
    public CacheManager cacheManager() {
        List<CaffeineCache> caches = Arrays.stream(CacheType.values())
            .map(cache -> new CaffeineCache(cache.getCacheName(), Caffeine.newBuilder().recordStats()
                    .expireAfterWrite(cache.getExpiredAfterWrite(), TimeUnit.SECONDS)
                    .maximumSize(cache.getMaximumSize())
                    .build()
                )
            )
            .toList();
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(caches);
        return simpleCacheManager;
    }

    @Getter
    @RequiredArgsConstructor
    public enum CacheType {
        CURRENT_USER("currentUser", 10 * TIME_1MIN, MEMORY_10KB)
        , USER("user", 10, MEMORY_10KB)
        , USER_SETTING("userSetting", 10, MEMORY_10KB)
        , CONFIGURATIONS("configurations", 1 * TIME_1MIN, MEMORY_10KB)
        , COMPANIES("companies", 5, MEMORY_10KB)
        , USER_COMPANIES("userCompanies", 5, MEMORY_10KB)
        , CATEGORY_TREE("categoryTree", 5 * TIME_1MIN, MEMORY_10KB)
        , CATEGORY_IDS("categoryIds", 5 * TIME_1MIN, MEMORY_10KB)
        ;

        private final String cacheName;
        private final int expiredAfterWrite; // seconds
        private final int maximumSize; // bytes
    }
}
