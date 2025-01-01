package com.template.spring_mongodb.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@ToString
@SuppressWarnings({"java:S1700", "java:S1948"})
public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonValue
    private final String message;
    private final List<Object> arguments;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public Message(@NonNull String message, Object... arguments) {
        this.message = message;
        this.arguments = arguments == null ? null : List.of(arguments);
    }
}
