package com.template.spring_mongodb.mapper;

import com.template.spring_mongodb.domain.user.CurrentUser;
import com.template.spring_mongodb.domain.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    CurrentUser of(User source);
}