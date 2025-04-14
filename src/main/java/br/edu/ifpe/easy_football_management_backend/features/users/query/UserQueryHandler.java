package br.edu.ifpe.easy_football_management_backend.features.users.query;

import br.edu.ifpe.easy_football_management_backend.infrestructure.security.TokenService;
import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import br.edu.ifpe.easy_football_management_backend.features.auth.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserQueryHandler {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    public UserQueryHandler(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public Optional<User> findUserByEmail(String authHeader) {
        String token = authHeader.substring(7);

        String email = tokenService.extractID(token);
        Optional<User> users = userRepository.findByEmail(email);
        return users;
    }

}
