package br.edu.ifpe.easy_football_management_backend.Presentation;

import br.edu.ifpe.easy_football_management_backend.Domain.Entity.User;
import br.edu.ifpe.easy_football_management_backend.Infrestructure.Repository.UserRepository;
import br.edu.ifpe.easy_football_management_backend.Infrestructure.Security.TokenService;
import br.edu.ifpe.easy_football_management_backend.dto.LoginResquetDTO;
import br.edu.ifpe.easy_football_management_backend.dto.RegisterRequestDTO;
import br.edu.ifpe.easy_football_management_backend.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginResquetDTO body) {
        User user = this.userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity login(@RequestBody RegisterRequestDTO body) {
        Optional<User> user = this.userRepository.findByEmail(body.email());
        if(user.isEmpty()) {
            User newUser = User.builder()
                    .name(body.name())
                    .email(body.email())
                    .password(passwordEncoder.encode(body.password()))
                    .build();
            this.userRepository.save(newUser);
            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
