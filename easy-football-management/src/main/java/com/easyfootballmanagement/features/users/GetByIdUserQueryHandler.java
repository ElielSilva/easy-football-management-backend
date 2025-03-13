package com.easyfootballmanagement.features.users;

import com.easyfootballmanagement.application.common.interfaces.IRequestHandler;
import com.easyfootballmanagement.domain.entities.Users;
import com.easyfootballmanagement.infrastructure.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service("GetByIdUserQueryHandler")
public class GetByIdUserQueryHandler implements IRequestHandler<GetByIdUserQuery, Users> {

    private final UserRepository repository;

    public GetByIdUserQueryHandler(UserRepository userRepository) {
        this.repository = userRepository;
    }


    @SneakyThrows()
    @Override
    public Users handle(GetByIdUserQuery request) {
        return repository.findById(request.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
}
