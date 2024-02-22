package com.example.movie_ver2.schedule.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class SearchSchedulesResponseDto {
    private LocalDateTime startTime;
}
