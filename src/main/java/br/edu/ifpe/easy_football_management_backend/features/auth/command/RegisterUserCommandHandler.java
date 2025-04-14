package br.edu.ifpe.easy_football_management_backend.features.auth.command;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Team;
import br.edu.ifpe.easy_football_management_backend.domain.entity.TeamRepository;
import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import br.edu.ifpe.easy_football_management_backend.features.auth.RegisterUserCommand;
import br.edu.ifpe.easy_football_management_backend.features.auth.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserCommandHandler {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserCommandHandler(UserRepository userRepository, TeamRepository teamRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
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

        User userCreated = userRepository.save(user);
        System.out.println(userCreated.getName());
        Team team = Team.builder()
                .name(userCreated.getName())
                .urlImage("url")
                .user(userCreated)
                .build();
        teamRepository.save(team);

    }
}