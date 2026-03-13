package com.example.meetingroom.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateReservationRequest {

    @NotNull
    private Long roomId;

    @NotNull
    private Long organizerId;

    @NotNull
    private LocalDateTime start;

    @NotNull
    private LocalDateTime end;

}
