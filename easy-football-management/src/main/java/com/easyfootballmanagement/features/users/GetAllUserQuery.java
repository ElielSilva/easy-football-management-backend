package com.easyfootballmanagement.features.users;

import com.easyfootballmanagement.application.common.interfaces.IRequest;
import com.easyfootballmanagement.domain.entities.Users;

import java.util.List;

public class GetAllUserQuery implements IRequest<List<Users>> {
    public GetAllUserQuery() {
    }
}
