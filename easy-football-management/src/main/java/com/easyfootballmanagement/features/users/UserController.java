package com.easyfootballmanagement.features.users;

import com.easyfootballmanagement.application.mediator.Mediator;
import com.easyfootballmanagement.domain.entities.Users;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "Endpoints responsavel para o gerenciamento dos usuarios")
public class UserController {

    private final Mediator mediator;

    public UserController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/")
    public ResponseEntity<Users> createUser(@RequestBody CreateUserCommand createUserCommand) {
        return new ResponseEntity<>(mediator.send(createUserCommand), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Users>> getUsers() {
        return new ResponseEntity<>(mediator.send(new GetAllUserQuery()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Users>> getUsersById(@PathVariable Long id) {
        return new ResponseEntity<>(mediator.send(new GetAllUserQuery()), HttpStatus.OK);
    }
}
