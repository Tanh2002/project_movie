package com.project_movie.services;

import com.project_movie.dtos.RoomDTO;

import java.util.List;

public interface IRoomService {
    List<RoomDTO> getRooms(Integer movieId, Integer branchId, String startDate, String startTime);
}
