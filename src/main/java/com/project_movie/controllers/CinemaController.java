package com.project_movie.controllers;

import com.project_movie.dtos.CinemaDTO;
import com.project_movie.services.ICinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/api/cinemas")
public class CinemaController {
    @Autowired
    private ICinemaService cinemaService;

    @GetMapping
    private ResponseEntity<List<CinemaDTO>> getCinemaesThatShowTheMovie(@RequestParam Integer movieId){
        return new ResponseEntity<>(cinemaService.getCinemaesThatShowTheMovie(movieId), HttpStatus.OK);
    }
}
