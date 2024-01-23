package com.example.movie_ver2.hall.dto;

import com.example.movie_ver2.hall.entity.Hall;
import com.example.movie_ver2.schedule.entity.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class HallInfoDto {
    private Long id;
    private String name;
    private Integer floor;
    private Integer seats;
    private List<Long> schedules;

    @Builder
    public HallInfoDto(Long id, String name, Integer floor, Integer seats, List<Long> schedules){
        this.id = id;
        this.name = name;
        this.floor = floor;
        this.seats = seats;
        this.schedules = schedules;
    }

    public static HallInfoDto of(Hall hall) {
        return HallInfoDto.builder()
                .id(hall.getId())
                .name(hall.getName())
                .floor(hall.getFloor())
                .seats(hall.getSeats())
                .schedules(hall.getSchedules().stream().map(Schedule::getId).collect(Collectors.toList()))
                .build();
    }
}
