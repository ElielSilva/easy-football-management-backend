package com.easyfootballmanagement.Dtos;

import io.swagger.v3.oas.annotations.media.DependentRequired;

public class UserDtoRequest {
    @DependentRequired
    public String fullName;
    @DependentRequired
    public String email;
    public String phone;
    public String urlImg;
}
