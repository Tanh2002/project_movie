package com.project_movie.services;

import com.project_movie.dtos.CinemaDTO;

import java.util.List;

public interface ICinemaService {
    List<CinemaDTO> getCinemaesThatShowTheMovie(Integer movieId);
}
