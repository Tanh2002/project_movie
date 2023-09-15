package com.project_movie.services;

import com.project_movie.dtos.ScheduleDTO;
import com.project_movie.repositories.IScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService implements IScheduleService {
    @Autowired
    private IScheduleRepository scheduleRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<String> getStartTimes(Integer movieId, Integer cinemaId, LocalDate startDate) {
        return scheduleRepository.getStartTimeByMovie_IdAndCinema_IdAndStartDate(movieId,cinemaId,startDate)
                .stream().map(localTime -> localTime.format(DateTimeFormatter.ofPattern("HH:mm")))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDTO> getSchedules(Integer movieId, Integer cinemaId, String startDate, String startTime, Integer roomId) {
        return scheduleRepository.getSchedulesByMovie_IdAndCinema_IdAndStartDateAndStartTimeAndRoom_Id(movieId,cinemaId
                        ,LocalDate.parse(startDate), LocalTime.parse(startTime), roomId)
                .stream().map(schedule -> modelMapper.map(schedule,ScheduleDTO.class))
                .collect(Collectors.toList());
    }
}
