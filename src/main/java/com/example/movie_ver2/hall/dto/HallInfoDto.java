package com.example.movie_ver2.hall.dto;

import com.example.movie_ver2.hall.entity.Hall;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HallInfoDto {
    private Long id;
    private String name;
    private Integer floor;
    private Integer seats;

    @Builder
    public HallInfoDto(Long id, String name, Integer floor, Integer seats){
        this.id = id;
        this.name = name;
        this.floor = floor;
        this.seats = seats;
    }

    public static HallInfoDto of(Hall hall) {
        return HallInfoDto.builder()
                .id(hall.getId())
                .name(hall.getName())
                .floor(hall.getFloor())
                .seats(hall.getSeats())
                .build();
    }
}
