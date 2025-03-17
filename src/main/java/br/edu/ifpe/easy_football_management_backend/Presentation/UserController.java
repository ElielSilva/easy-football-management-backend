package br.edu.ifpe.easy_football_management_backend.Presentation;

import br.edu.ifpe.easy_football_management_backend.Domain.Entity.User;
import br.edu.ifpe.easy_football_management_backend.Infrestructure.Security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable UUID userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("sucesso");
    }


//    @DeleteMapping("/{userId}")
//    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
//        userService.deleteUser(userId);
//        return ResponseEntity.noContent().build();
//    }
}
