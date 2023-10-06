package com.example.movie_ver2.schedule.dto;


import com.example.movie_ver2.core.enums.Sex;
import com.example.movie_ver2.member.entity.Member;
import com.example.movie_ver2.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class CreateScheduleRequestDto {

    private String movieTitle;
    private Long screenroomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    public Schedule toEntity() {

        return Schedule.builder()

                .build();
    }
}
