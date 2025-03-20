package br.edu.ifpe.easy_football_management_backend.features.users.command;

import br.edu.ifpe.easy_football_management_backend.infrestructure.security.TokenService;
import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import br.edu.ifpe.easy_football_management_backend.features.auth.UserRepository;
import br.edu.ifpe.easy_football_management_backend.features.users.UserUpdateDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserCommandHandler {
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public UserCommandHandler(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public User updateUsers(String authHeader, UserUpdateDTO userUpdateDTO ) {
        String token = authHeader.substring(7);
        String email = tokenService.extractEmail(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (userUpdateDTO.name() != null) {
            user.setName(userUpdateDTO.name());
        }
        if (userUpdateDTO.email() != null) {
            user.setEmail(userUpdateDTO.email());
        }
        if (userUpdateDTO.password() != null) {
            user.setPassword(userUpdateDTO.password());
        }
        if (userUpdateDTO.phone() != null) {
            user.setPhone(userUpdateDTO.phone());
        }
        if (userUpdateDTO.urlImage() != null) {
            user.setUrlImage(userUpdateDTO.urlImage());
        }
        userRepository.save(user);
        return user;
    }

    public void deleteteUsers(String authHeader) {
        String token = authHeader.substring(7);

        String email = tokenService.extractEmail(token);
        Optional<User> user = userRepository.findByEmail(email);
        user.ifPresent(u -> {
            u.setDeleted(true);
            userRepository.save(u);
        });
    }
}

