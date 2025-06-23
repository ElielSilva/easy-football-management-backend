package br.edu.ifpe.easy_football_management_backend.features.auth.command;

import br.edu.ifpe.easy_football_management_backend.domain.entity.*;
import br.edu.ifpe.easy_football_management_backend.features.auth.RegisterUserCommand;
import br.edu.ifpe.easy_football_management_backend.infrestructure.security.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegisterUserCommandHandler {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public RegisterUserCommandHandler(UserRepository userRepository, TeamRepository teamRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public String handle(RegisterUserCommand command) {
        if (userRepository.findByEmail(command.email()).isPresent()) {
            throw new IllegalArgumentException("Email already exists.");
        }

        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);

        if (command.isAdmin()) {
            roles.add(Role.ADMIN);
        }

        User user = User.builder()
                .name(command.name())
                .email(command.email())
                .phone("")
                .password(passwordEncoder.encode(command.password()))
                .urlImage("")
                .role(roles)
                .deleted(false)
                .build();

        User userCreated = userRepository.save(user);
        System.out.println(userCreated.getName());
        Team team = Team.builder()
                .name(userCreated.getName())
                .urlImage("url")
                .user(userCreated)
                .build();
        teamRepository.save(team);

        return tokenService.generateToken(userCreated);
    }
}