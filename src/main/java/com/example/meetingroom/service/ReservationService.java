package com.example.meetingroom.service;

import com.example.meetingroom.dto.CreateReservationRequest;
import com.example.meetingroom.entity.Reservation;
import com.example.meetingroom.entity.Room;
import com.example.meetingroom.entity.User;
import com.example.meetingroom.repository.ReservationRepository;
import com.example.meetingroom.repository.RoomRepository;
import com.example.meetingroom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public Reservation createReservation(CreateReservationRequest req){

        Room room = roomRepository.findById(req.getRoomId()).orElseThrow();
        User organizer = userRepository.findById(req.getOrganizerId()).orElseThrow();

        Reservation reservation = new Reservation();

        reservation.setRoom(room);
        reservation.setOrganizer(organizer);
        reservation.setStartTime(req.getStart());
        reservation.setEndTime(req.getEnd());

        return reservationRepository.save(reservation);
    }

}