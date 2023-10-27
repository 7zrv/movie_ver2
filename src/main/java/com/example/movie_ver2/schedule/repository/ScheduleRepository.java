package com.example.movie_ver2.schedule.repository;

import com.example.movie_ver2.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT e FROM Schedule e WHERE e.hall.id = :hallId AND DATE(e.startTime) = :screenDate")
    List<Schedule> findScheduleByHallIdAndScreenDate(@Param("hallId") Long hallId, @Param("screenDate") LocalDate screenDate);

}
