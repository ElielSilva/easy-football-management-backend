package com.easyfootballmanagement.Dtos;

import java.util.List;

public class TeamsFullDtoRequest {
    public String name;
    public String urlImg;
    public UserDtoRequest users;
    public List<PlayerDtoRequest> players;
}
