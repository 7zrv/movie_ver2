package com.example.movie_ver2.schedule.service;


import com.example.movie_ver2.schedule.dto.CreateScheduleRequestDto;
import com.example.movie_ver2.schedule.entity.Schedule;
import com.example.movie_ver2.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    ScheduleRepository scheduleRepository;

    public Schedule saveSchedule(CreateScheduleRequestDto requestDto) {
        return scheduleRepository.save(requestDto.toEntity());
    }


}
