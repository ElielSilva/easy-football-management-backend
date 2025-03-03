package com.easyfootballmanagement.features.users;

import com.easyfootballmanagement.domain.entities.Users;
import org.mapstruct.factory.Mappers;

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Users map(CreateUserCommand createUserCommand);
}
