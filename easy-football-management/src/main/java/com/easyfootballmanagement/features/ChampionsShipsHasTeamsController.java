package com.easyfootballmanagement.features;

import com.easyfootballmanagement.application.common.exception.BusinessException;
import com.easyfootballmanagement.domain.entities.ChampionsShipsHasTeams;
import com.easyfootballmanagement.Services.ChampionsShipsHasTeamService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/championsshipshasteams")
@Tag(name = "ChampionsShipsHasTeams", description = "Endpoints responsavel para o gerenciamento dos campeonatos com os temes")
public class ChampionsShipsHasTeamsController {

    @Autowired
    private ChampionsShipsHasTeamService service;

    @GetMapping
    public ResponseEntity<List<ChampionsShipsHasTeams>> get() {
        var result = service.get();
        System.out.println(result);
        if (result != null)
            return new ResponseEntity<List<ChampionsShipsHasTeams>>(result, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/id")
    public ResponseEntity<ChampionsShipsHasTeams> GetById(@RequestParam long id){
        ChampionsShipsHasTeams result = null;
        try {
            result = service.getById(id);
            return new ResponseEntity<ChampionsShipsHasTeams>(result, HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody ChampionsShipsHasTeams model) {
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
    public ResponseEntity update(@RequestBody ChampionsShipsHasTeams model) {
        try {
            service.update(model);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
