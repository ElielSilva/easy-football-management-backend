package com.easyfootballmanagement.Controllers;

import com.easyfootballmanagement.Dtos.UserDtoRequest;
import com.easyfootballmanagement.Exception.BusinessException;
import com.easyfootballmanagement.Mapper.UserToMap;
import com.easyfootballmanagement.Models.Users;
import com.easyfootballmanagement.Services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "Endpoints responsavel para o gerenciamento dos usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<Users>> get(){
        var result = userService.get();
        System.out.println(result);
        if (result != null)
            return new ResponseEntity<List<Users>>(result, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getById(@RequestParam long id){
        Users result = null;
        try {
            result = userService.getById(id);
            return new ResponseEntity<Users>(result, HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity insertUser(@RequestBody UserDtoRequest model) {
        try {
            userService.insert(UserToMap.mapToDtoForUser(model));
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (BusinessException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteById(@RequestParam long id) {
        try {
            userService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity UpdateUser(@RequestBody Users model) {
        try {
            userService.update(model);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
