package com.template.spring_mongodb.mapper;

import com.template.spring_mongodb.domain.user.CurrentUser;
import com.template.spring_mongodb.domain.user.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-01T23:44:04+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.jar, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public CurrentUser of(User source) {
        if ( source == null ) {
            return null;
        }

        CurrentUser.CurrentUserBuilder currentUser = CurrentUser.builder();

        currentUser.id( source.getId() );
        currentUser.username( source.getUsername() );
        currentUser.name( source.getName() );
        currentUser.email( source.getEmail() );

        return currentUser.build();
    }
}
