package com.easyfootballmanagement.features.users;

import com.easyfootballmanagement.application.common.interfaces.IRequestHandler;
import com.easyfootballmanagement.domain.entities.Users;
import com.easyfootballmanagement.infrastructure.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("UpdateUserCommandHandler")
public class UpdateUserCommandHandler implements IRequestHandler<UpdateUserCommand, Users> {

    private final UserRepository repository;
    private final UserMapper userMapper;

    public UpdateUserCommandHandler(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    @SneakyThrows()
    @Override
    public Users handle(UpdateUserCommand request) {
        UpdateUserValidator.validate(request);
        Optional<Users> u = repository.findById(request.getId());
        if (u.isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        Users entity = userMapper.updateUserCommandToUsers(request);
        System.out.println("entity: " + entity);
        return  repository.save(entity);
    }
}
