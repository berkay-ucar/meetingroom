package com.example.meetingroom.service;

import com.example.meetingroom.dto.CalendarEventDTO;
import com.example.meetingroom.dto.CreateReservationRequest;
import com.example.meetingroom.entity.Group;
import com.example.meetingroom.entity.Reservation;
import com.example.meetingroom.entity.Room;
import com.example.meetingroom.entity.User;
import com.example.meetingroom.repository.GroupRepository;
import com.example.meetingroom.repository.ReservationRepository;
import com.example.meetingroom.repository.RoomRepository;
import com.example.meetingroom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public Reservation createReservation(CreateReservationRequest request) {

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow();

        User organizer = userRepository.findById(request.getOrganizerId())
                .orElseThrow();

        Group group = null;
        if(request.getGroupId()!=null){
            group = groupRepository.findById(request.getGroupId()).orElseThrow();
        }

        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        reservation.setOrganizer(organizer);
       // reservation.setGroup(group);
        reservation.setStartTime(request.getStart());
        reservation.setEndTime(request.getEnd());

        Set<User> participants =
                new HashSet<>(userRepository.findAllById(request.getParticipants()));

        reservation.setParticipants(participants);



        return reservationRepository.save(reservation);
    }

    public List<CalendarEventDTO> getUserCalendar(Long userId){

        List<Reservation> reservations =
                reservationRepository.findUserMeetings(userId);

        return reservations.stream()
                .map(r -> new CalendarEventDTO(
                        r.getId(),
                        r.getRoom().getName(),
                        r.getStartTime(),
                        r.getEndTime()
                ))
                .toList();
    }
}