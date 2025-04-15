package br.edu.ifpe.easy_football_management_backend.features.auth.query;

import br.edu.ifpe.easy_football_management_backend.domain.entity.UserRepository;
import br.edu.ifpe.easy_football_management_backend.infrestructure.security.TokenService;
import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import br.edu.ifpe.easy_football_management_backend.features.auth.LoginUserQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginUserQueryHandler {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginUserQueryHandler(UserRepository userRepository, TokenService tokenService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public String handle(LoginUserQuery query) {
        User user = userRepository.findByEmail(query.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(query.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return tokenService.generateToken(user);
    }
}