package br.edu.ifpe.easy_football_management_backend.features.auth;

import br.edu.ifpe.easy_football_management_backend.features.auth.command.RegisterUserCommandHandler;
import br.edu.ifpe.easy_football_management_backend.features.auth.query.LoginUserQueryHandler;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegisterUserCommandHandler registerUserCommandHandler;
    private final LoginUserQueryHandler loginUserQueryHandler;

    public AuthController(
            RegisterUserCommandHandler registerUserCommandHandler, LoginUserQueryHandler loginUserQueryHandler
    ) {
        this.registerUserCommandHandler = registerUserCommandHandler;
        this.loginUserQueryHandler = loginUserQueryHandler;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseAuthDTO> register(@Valid @RequestBody RegisterUserCommand command) {

        return ResponseEntity.ok(new ResponseAuthDTO(registerUserCommandHandler.handle(command)));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseAuthDTO> login(@RequestBody LoginUserQuery query) {
        String token = loginUserQueryHandler.handle(query);
        return ResponseEntity.ok(new ResponseAuthDTO(token));
    }
}

