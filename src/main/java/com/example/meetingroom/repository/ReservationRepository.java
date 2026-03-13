package com.example.meetingroom.repository;


import com.example.meetingroom.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {


    @Query("""
    select r from Reservation r
    left join r.participants p
    where p.id = :userId
    or r.organizer.id = :userId
    """)
    List<Reservation> findUserMeetings(Long userId);

}