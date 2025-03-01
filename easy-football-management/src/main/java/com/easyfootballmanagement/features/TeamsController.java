package com.easyfootballmanagement.features;

import com.easyfootballmanagement.application.common.exception.BusinessException;
import com.easyfootballmanagement.domain.entities.Teams;
import com.easyfootballmanagement.Services.TeamsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@Tag(name = "Teams", description = "Endpoints responsavel para o gerenciamento dos temes")
public class TeamsController {

    @Autowired
    private TeamsService service;

    @GetMapping
    public ResponseEntity<List<Teams>> get() {
        var result = service.get();
        System.out.println(result);
        if (result != null)
            return new ResponseEntity<List<Teams>>(result, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public String getQuantityPlayer(@PathVariable Long id) {
        try {
            Teams team = service.getById(id);

            // exemplo lazy
            int qnt = team.getPlayers().size();
            return "O time: " + team.getName() + " tem " + qnt + " jogadores.";
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/id")
    public ResponseEntity<Teams> GetById(@RequestParam long id){
        Teams result = null;
        try {
            result = service.getById(id);
            return new ResponseEntity<Teams>(result, HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody Teams model) {
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
    public ResponseEntity update(@RequestBody Teams model) {
        try {
            service.update(model);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
