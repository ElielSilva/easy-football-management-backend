package com.easyfootballmanagement.Controllers;

import com.easyfootballmanagement.Exception.BusinessException;
import com.easyfootballmanagement.Models.Results;
import com.easyfootballmanagement.Services.ResultsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@Tag(name = "Results", description = "Endpoints responsavel para o gerenciamento dos resultados")
public class ResultsController {

    @Autowired
    private ResultsService service;

    @GetMapping
    public ResponseEntity<List<Results>> get() {
        var result = service.get();
        System.out.println(result);
        if (result != null)
            return new ResponseEntity<List<Results>>(result, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/id")
    public ResponseEntity<Results> GetById(@RequestParam long id){
        Results result = null;
        try {
            result = service.getById(id);
            return new ResponseEntity<Results>(result, HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody Results model) {
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
    public ResponseEntity update(@RequestBody Results model) {
        try {
            service.update(model);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
