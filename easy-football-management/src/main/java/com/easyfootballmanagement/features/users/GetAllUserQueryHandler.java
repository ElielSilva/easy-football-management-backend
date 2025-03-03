package com.easyfootballmanagement.features.users;


import com.easyfootballmanagement.application.common.interfaces.IRequestHandler;
import com.easyfootballmanagement.domain.entities.Users;
import com.easyfootballmanagement.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GetAllUserQueryHandler")
public class GetAllUserQueryHandler implements IRequestHandler<GetAllUserQuery, List<Users>> {

    private final UserRepository repository;

    public GetAllUserQueryHandler(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Users> handle(GetAllUserQuery request) {
        return repository.findAll();
    }
}
