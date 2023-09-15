package com.project_movie.services;

import com.project_movie.dtos.BookingRequestDTO;
import com.project_movie.entities.Bill;
import com.project_movie.entities.Schedule;
import com.project_movie.entities.Ticket;
import com.project_movie.entities.User;
import com.project_movie.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BillService implements IBillService {
    @Autowired
    private IScheduleRepository scheduleRepository;
    @Autowired
    private ITicketRepository ticketRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ISeatRepository seatRepository;
    @Autowired
    private IBillRepository billRepository;

    @Override
    @Transactional
    public void createNewBill(BookingRequestDTO bookingRequestDTO) throws RuntimeException {

        //Lấy ra lịch
        Schedule schedule = scheduleRepository.getById(bookingRequestDTO.getScheduleId());
        //Lấy ra người dùng
        User user = userRepository.getById(bookingRequestDTO.getUserId());

        //Lưu Bill gồm thông tin người dùng xuống trước
        Bill billToCreate = new Bill();
        billToCreate.setUser(user);
        billToCreate.setCreatedTime(LocalDateTime.now());
        Bill createdBill = billRepository.save(billToCreate);

        //Với mỗi ghế ngồi check xem đã có ai đặt chưa, nếu rồi thì throw, roll back luôn còn ko
        //thì đóng gói các thông tin ghế và lịch vào vé và lưu xuống db
        bookingRequestDTO.getListSeatIds().forEach(seatId->{
            if(!ticketRepository.findTicketsBySchedule_IdAndSeat_Id(schedule.getId(),seatId)
                    .isEmpty()){// Nếu đã có người đặt vé ghế đó ở lịch cụ thể đó thì
                throw new RuntimeException("Đã có người nhanh tay hơn đặt ghế, mời bạn chọn lại!");
            }
            // đóng gói lịch, seat và bill vào từng vé rồi add vào list vé
            Ticket ticket = new Ticket();
            ticket.setSchedule(schedule);
            ticket.setSeat(seatRepository.getById(seatId));
            ticket.setBill(createdBill);
            ticket.setQrImageURL("https://www.google.com.vn/url?sa=i&url=https%3A%2F%2Fqrcode-gen.com%2F&psig=AOvVaw2ICN4e9xeHGzvjEfwzkvJ0&ust=1694851594351000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCPixzayUrIEDFQAAAAAdAAAAABAE");
            ticketRepository.save(ticket);
        });

    }
}
