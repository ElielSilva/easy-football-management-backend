package com.easyfootballmanagement.features.users;

import com.easyfootballmanagement.application.common.interfaces.IRequest;
import com.easyfootballmanagement.domain.entities.Users;



public class UpdateUserCommand implements IRequest<Users> {
    private long id;
    private String fullName;
    private String phone;
    private String img;

    public UpdateUserCommand() {
    }

    public UpdateUserCommand(String email, String name, String phone, String img) {
        this.fullName = name;
        this.phone = phone;
        this.img = img;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
