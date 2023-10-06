package com.example.movie_ver2.schedule.repository;

import com.example.movie_ver2.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
