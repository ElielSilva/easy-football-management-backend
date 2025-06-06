package br.edu.ifpe.easy_football_management_backend.features.users.query;

import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import br.edu.ifpe.easy_football_management_backend.domain.entity.UserRepository;
import br.edu.ifpe.easy_football_management_backend.features.users.UserDTO;
import br.edu.ifpe.easy_football_management_backend.features.users.UserMapper;
import br.edu.ifpe.easy_football_management_backend.infrestructure.security.TokenService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserQueryHandler {

    public final UserRepository userRepository;
    public final TokenService tokenService;
    private final UserMapper mapper;

    public UserQueryHandler(UserRepository userRepository, TokenService tokenService, UserMapper mapper) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.mapper = mapper;
    }


    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public UserDTO get(String authHeader) {
        String id = tokenService.extractID(authHeader);
        Optional<User> user = userRepository.findById(UUID.fromString(id));

        if (user.isEmpty()) {
            throw new RuntimeException("Usuário não existe");
        }

        return mapper.toDTO(user.get());
    }

}
