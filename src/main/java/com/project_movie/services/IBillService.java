package com.project_movie.services;

import com.project_movie.dtos.BookingRequestDTO;
import org.springframework.transaction.annotation.Transactional;

public interface IBillService {
    @Transactional
    void createNewBill(BookingRequestDTO bookingRequestDTO) throws RuntimeException;
}
