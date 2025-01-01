package com.template.spring_mongodb.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

public record ApiResponseBody<T>(
    @JsonIgnore
    int code,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Message message,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Map<String, Message> errors
) {
}
