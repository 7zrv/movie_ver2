package com.example.movie_ver2.schedule.dto;

import com.example.movie_ver2.schedule.entity.Schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SearchScheduleResponseDto {

    private Long id;
    private Long hallId;
    private String hallName;
    private Long movieId;
    private String movieTitle;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Builder
    public SearchScheduleResponseDto(Long id, Long hallId, String hallName, Long movieId, String movieTitle, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.hallId = hallId;
        this.hallName = hallName;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static SearchScheduleResponseDto of(Schedule schedule) {
        return SearchScheduleResponseDto.builder()
                .id(schedule.getId())
                .hallId(schedule.getHall().getId())
                .hallName(schedule.getHall().getName())
                .movieId(schedule.getMovie().getId())
                .movieTitle(schedule.getMovie().getTitle())
                .startTime(schedule.getStartTime())
                .endTime(schedule.getEndTime())
                .build();
    }
}
