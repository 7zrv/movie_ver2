package com.example.movie_ver2.hall.dto;

import com.example.movie_ver2.hall.entity.Hall;
import com.example.movie_ver2.theater.entity.Theater;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class RequestHallDto {
    //@NotBlank
    @Size(max = 20, min = 2)
    private String name;
    private Integer floor;
    @Positive
    private Integer seats;

    public Hall toEntity(Theater theater) {
        return Hall.builder()
                .name(this.name)
                .floor(this.floor)
                .seats(this.seats)
                .theater(theater)
                .build();
    }
}
