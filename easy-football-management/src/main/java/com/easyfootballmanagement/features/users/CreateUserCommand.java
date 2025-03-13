package com.easyfootballmanagement.features.users;

import com.easyfootballmanagement.application.common.interfaces.IRequest;
import com.easyfootballmanagement.domain.entities.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CreateUserCommand implements IRequest<Users> {

    @Min(value = 3, message = "len min is 3")
    @Max(value = 50, message = "len mix is 50")
    @NotNull(message = "Name cannot be null")
    private String fullName;
    @Email(message = "Email should be valid")
    @Max(150)
    private String email;
    @Min(11)
    @Max(20)
    private String phone;

    public CreateUserCommand() {
    }

    public CreateUserCommand(String phone, String email, String fullName) {
        this.phone = phone;
        this.email = email;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
