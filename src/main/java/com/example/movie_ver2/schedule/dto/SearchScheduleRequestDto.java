package com.example.movie_ver2.schedule.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class SearchScheduleRequestDto {
    private Long hallId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate screenDate;

}
