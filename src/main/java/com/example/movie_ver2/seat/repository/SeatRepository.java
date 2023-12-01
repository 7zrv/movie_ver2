package com.example.movie_ver2.seat.repository;


import com.example.movie_ver2.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findAllByScheduleId(Long scheduleId);

    List<Seat> findByReserveAvailFalseAndSoldoutFalse();
}
