package com.easyfootballmanagement.Controllers;

import com.easyfootballmanagement.Exception.BusinessException;
import com.easyfootballmanagement.Models.Players;
import com.easyfootballmanagement.Services.PlayerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@Tag(name = "Players", description = "Endpoints responsavel para o gerenciamento dos players")
public class PlayersController {

    @Autowired
    private PlayerService service;

    @GetMapping
    public ResponseEntity<List<Players>> get() {
        var result = service.get();
        System.out.println(result);
        if (result != null)
            return new ResponseEntity<List<Players>>(result, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/id")
    public ResponseEntity<Players> GetById(@RequestParam long id){
        Players result = null;
        try {
            result = service.getById(id);
            return new ResponseEntity<Players>(result, HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody Players model) {
        try {
            service.insert(model);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (BusinessException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteById(@RequestParam long id) {
        try {
            service.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Players model) {
        try {
            service.update(model);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
