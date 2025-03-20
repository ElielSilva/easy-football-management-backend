package br.edu.ifpe.easy_football_management_backend.features.users;

import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import br.edu.ifpe.easy_football_management_backend.features.users.command.UserCommandHandler;
import br.edu.ifpe.easy_football_management_backend.features.users.query.UserQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
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

    @GetMapping("/profile")
    public ResponseEntity<Optional<User>> getUsers(@RequestHeader("Authorization") String authHeader) {
        Optional<User> users = userQueryHandler.findUserByEmail(authHeader);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User> > getUsers() {
        List<User> users = userQueryHandler.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser (@RequestHeader("Authorization") String authHeader, @RequestBody UserUpdateDTO userUpdateDTO) {
        User user = userCommandHandler.updateUsers(authHeader, userUpdateDTO);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser (@RequestHeader("Authorization") String authHeader) {
        userCommandHandler.deleteteUsers(authHeader);
        return ResponseEntity.ok("sucesso");
    }
}
