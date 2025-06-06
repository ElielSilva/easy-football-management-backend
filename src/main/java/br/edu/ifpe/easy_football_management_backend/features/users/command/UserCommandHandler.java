package br.edu.ifpe.easy_football_management_backend.features.users.command;

import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import br.edu.ifpe.easy_football_management_backend.domain.entity.UserRepository;
import br.edu.ifpe.easy_football_management_backend.features.users.UserUpdateDTO;
import br.edu.ifpe.easy_football_management_backend.infrestructure.security.TokenService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserCommandHandler {
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public UserCommandHandler(
            UserRepository userRepository,
            TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public User updateUsers(String authHeader, UserUpdateDTO userUpdateDTO) {
        String token = authHeader.substring(7);
        UUID id = UUID.fromString(tokenService.extractID(token));
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        userRepository.save(getUserUpdate(user, userUpdateDTO));
        return user;
    }

    private User getUserUpdate(User user, UserUpdateDTO userUpdateDTO) {
        if (userUpdateDTO.name() != null && !userUpdateDTO.name().isBlank()) {
            user.setName(userUpdateDTO.name());
        }
        if (userUpdateDTO.email() != null && !userUpdateDTO.email().isBlank()) {
            user.setEmail(userUpdateDTO.email());
        }
        if (userUpdateDTO.phone() != null && !userUpdateDTO.phone().isBlank()) {
            user.setPhone(userUpdateDTO.phone());
        }
        if (userUpdateDTO.urlImage() != null && !userUpdateDTO.urlImage().isBlank()) {
            user.setUrlImage(userUpdateDTO.urlImage());
        }
        return user;
    }

    public void deleteteUsers(String authHeader) {
        String token = authHeader.substring(7);
        UUID id = UUID.fromString(tokenService.extractID(token));
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(u -> {
            u.setDeleted(true);
            userRepository.save(u);
        });
    }
}

