package br.edu.ifpe.easy_football_management_backend.features.auth.command;

import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import br.edu.ifpe.easy_football_management_backend.features.auth.RegisterUserCommand;
import br.edu.ifpe.easy_football_management_backend.features.auth.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserCommandHandler {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserCommandHandler(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void handle(RegisterUserCommand command) {
        if (userRepository.findByEmail(command.email()).isPresent()) {
            throw new IllegalArgumentException("Email already exists.");
        }

        User user = User.builder()
                .name(command.name())
                .email(command.email())
                .phone("")
                .password(passwordEncoder.encode(command.password()))
                .urlImage("")
                .deleted(false)
                .build();

        userRepository.save(user);
    }
}