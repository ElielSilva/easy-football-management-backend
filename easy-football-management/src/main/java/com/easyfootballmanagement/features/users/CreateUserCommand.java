package com.easyfootballmanagement.features.users;

import com.easyfootballmanagement.application.common.interfaces.IRequest;
import com.easyfootballmanagement.domain.entities.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserCommand implements IRequest<Users> {
    private String name;
    private String email;
    private String password;
    private String phone;

    public CreateUserCommand() {
    }

    public CreateUserCommand(String phone, String password, String email, String name) {
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.name = name;
    }

}
