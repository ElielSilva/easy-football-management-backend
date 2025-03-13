package com.easyfootballmanagement.features.users;
import com.easyfootballmanagement.application.common.interfaces.IRequestHandler;
import com.easyfootballmanagement.domain.entities.Users;
import com.easyfootballmanagement.infrastructure.repository.UserRepository;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CreateUserCommandHandler")
public class CreateUserCommandHandler implements IRequestHandler<CreateUserCommand, Users> {

    private final UserMapper userMapper;
    private final UserRepository repository;

    @Autowired
    public CreateUserCommandHandler(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.repository = userRepository;
    }

    @SneakyThrows
    @Override
    public Users handle(CreateUserCommand request) {
        CreateUserValidator.validate(request);
        return repository.save(userMapper.mapToCommandCreated(request));
    }

}
