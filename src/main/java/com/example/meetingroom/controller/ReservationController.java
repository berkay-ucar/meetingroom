package com.example.meetingroom.controller;

import com.example.meetingroom.dto.CalendarEventDTO;
import com.example.meetingroom.dto.CreateReservationRequest;
import com.example.meetingroom.entity.Reservation;
import com.example.meetingroom.entity.User;
import com.example.meetingroom.repository.UserRepository;
import com.example.meetingroom.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final UserRepository userRepository;

    @PostMapping
    public Reservation create(@RequestBody CreateReservationRequest request){
        return reservationService.createReservation(request);
    }

    @GetMapping("/calendar/{userId}")
    public List<CalendarEventDTO> calendar(@PathVariable Long userId){
        return reservationService.getUserCalendar(userId);
    }

    @GetMapping("/calendar/me")
    public List<CalendarEventDTO> myCalendar(Authentication auth){

        String email = auth.getName();


        User user = userRepository.findByEmail(email).orElseThrow();

        return reservationService.getUserCalendar(user.getId());

    }
}
