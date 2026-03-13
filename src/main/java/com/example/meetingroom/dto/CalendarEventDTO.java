package com.example.meetingroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CalendarEventDTO {

    private Long id;
    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
}