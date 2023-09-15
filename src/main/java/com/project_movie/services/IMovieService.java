package com.project_movie.services;

import com.project_movie.dtos.MovieDTO;

import java.util.List;

public interface IMovieService {
    List<MovieDTO> findAllShowingMovies();

    MovieDTO getById(Integer movieId);

    List<MovieDTO> findAllShowingMoviesByName(String keyword);
}
