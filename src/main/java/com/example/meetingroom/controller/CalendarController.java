package com.example.meetingroom.controller;

import com.example.meetingroom.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final ReservationRepository reservationRepository;

    @GetMapping
    public List<Map<String,Object>> calendar(){

        return reservationRepository.findAll().stream().map(r -> {

            Map<String,Object> event = new HashMap<>();

            event.put("id", r.getId());
            event.put("title", r.getRoom().getName());
            event.put("start", r.getStartTime());
            event.put("end", r.getEndTime());

            return event;

        }).toList();
    }

}