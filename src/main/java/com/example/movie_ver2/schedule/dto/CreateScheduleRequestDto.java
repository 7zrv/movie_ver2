package com.example.movie_ver2.schedule.dto;


import com.example.movie_ver2.core.enums.Sex;
import com.example.movie_ver2.member.entity.Member;
import com.example.movie_ver2.schedule.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class CreateScheduleRequestDto {

    private String movieTitle;
    private Long scrId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endTime;

    public Schedule toEntity() {

        return Schedule.builder()
                .scrId(scrId)
                .movieTitle(movieTitle)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
