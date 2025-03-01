package com.easyfootballmanagement.Mapper;

import com.easyfootballmanagement.Dtos.UserDtoRequest;
import com.easyfootballmanagement.domain.entities.Users;

public class UserToMap {

    public static Users mapToDtoForUser(UserDtoRequest req) {
        return Users
                .builder()
                .email(req.email)
                .phone(req.phone)
                .fullName(req.fullName)
                .urlImg(req.urlImg)
                .build();
    }
}
