package com.template.spring_mongodb.domain;

import com.template.spring_mongodb.common.CacheHelper;
import lombok.Getter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Getter
@Component
public class GlobalServiceContext {
    private final CacheHelper cacheHelper;


    public GlobalServiceContext(
            CacheHelper cacheHelper
    ) {
        this.cacheHelper = cacheHelper;
    }
}