package com.easyfootballmanagement.features.users;

import com.easyfootballmanagement.application.common.interfaces.IRequestHandler;
import com.easyfootballmanagement.domain.entities.Users;
import com.easyfootballmanagement.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service("CreateUserCommandHandler")
public class CreateUserCommandHandler implements IRequestHandler<CreateUserCommand, Users> {

    private final UserRepository repository;

    public CreateUserCommandHandler(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public Users handle(CreateUserCommand request) {
        Users user = UserMapper.INSTANCE.map(request);
        return repository.save(user);
    }
}
