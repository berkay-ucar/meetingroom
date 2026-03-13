package com.example.meetingroom.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
public class CreateReservationRequest {

    @NotNull
    private Long roomId;

    @NotNull
    private Long organizerId;

    @NotNull
    private LocalDateTime start;

    @NotNull
    private LocalDateTime end;


    private Long groupId; // opsiyonel


    private List<Long> participants; // kullanıcı ID’leri



}
