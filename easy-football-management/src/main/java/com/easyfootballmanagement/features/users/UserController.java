package com.easyfootballmanagement.features.users;

import com.easyfootballmanagement.application.mediator.Mediator;
import com.easyfootballmanagement.domain.entities.Users;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "Endpoints responsavel para o gerenciamento dos usuarios")
public class UserController {

    @Autowired
    private Mediator mediator;

    @PostMapping("/")
    public ResponseEntity<Users> createUser(@RequestBody CreateUserCommand createUserCommand) {
        System.out.println("Antes de enviar ao Mediator: " + createUserCommand.toString());
        return new ResponseEntity<>(mediator.send(createUserCommand), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Users>> getUsers() {
        return new ResponseEntity<>(mediator.send(new GetAllUserQuery()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUsersById(@PathVariable @NotNull @Min(1) Long id) {
        var query = new GetByIdUserQuery(id);
        return new ResponseEntity<>(mediator.send(query), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> UpdateUser(@PathVariable @NotNull @Min(1) Long id, @RequestBody UpdateUserCommand user) {
        user.setId(id);
        return new ResponseEntity<>(mediator.send(user), HttpStatus.OK);
    }
}
