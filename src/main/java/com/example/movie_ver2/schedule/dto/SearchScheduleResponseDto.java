package com.example.movie_ver2.schedule.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SearchScheduleResponseDto {

    private Long id;
    private Long hallId;
    private String HallName;
    private Long movieId;
    private Long movieTitle;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
