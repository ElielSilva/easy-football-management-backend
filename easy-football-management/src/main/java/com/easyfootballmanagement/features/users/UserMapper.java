package com.easyfootballmanagement.features.users;

import com.easyfootballmanagement.domain.entities.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "urlImg", ignore = true)
    @Mapping(target = "team", ignore = true)
    Users mapToCommandCreated(CreateUserCommand createUserCommand);

    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "img", target = "urlImg")
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "team", ignore = true)
    Users updateUserCommandToUsers(UpdateUserCommand updateUserCommand);
}
