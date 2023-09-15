package com.project_movie.services;

import com.project_movie.dtos.CinemaDTO;
import com.project_movie.repositories.ICinemaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaService implements ICinemaService {
    @Autowired
    private ICinemaRepository cinemaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CinemaDTO> getCinemaesThatShowTheMovie(Integer movieId) {
        return cinemaRepository.getCinemaThatShowTheMovie(movieId)
                .stream().map(cinema -> modelMapper.map(cinema,CinemaDTO.class))
                .collect(Collectors.toList());
    }
}
