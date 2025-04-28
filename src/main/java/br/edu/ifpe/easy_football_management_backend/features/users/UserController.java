package br.edu.ifpe.easy_football_management_backend.features.users;

import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import br.edu.ifpe.easy_football_management_backend.features.users.command.UserCommandHandler;
import br.edu.ifpe.easy_football_management_backend.features.users.query.UserQueryHandler;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserCommandHandler userCommandHandler;
    private final UserQueryHandler userQueryHandler;
//
    public UserController(
            UserCommandHandler userCommandHandler,
            UserQueryHandler userQueryHandler) {
        this.userCommandHandler = userCommandHandler;
        this.userQueryHandler = userQueryHandler;
    }

    @GetMapping("")
    public ResponseEntity<UserDTO> get(@RequestHeader("Authorization") String authHeader) {
        UserDTO user = userQueryHandler.get(authHeader);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User> > getAll() {
        List<User> users = userQueryHandler.getAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("")
    public ResponseEntity<User> updateUser (@RequestHeader("Authorization") String authHeader, @RequestBody UserUpdateDTO userUpdateDTO) {
        User user = userCommandHandler.updateUsers(authHeader, userUpdateDTO);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteUser (@RequestHeader("Authorization") String authHeader) {
        userCommandHandler.deleteteUsers(authHeader);
        return ResponseEntity.ok("sucesso");
    }
}
