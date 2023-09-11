package com.example.movie_ver2.theater.dto;

import com.example.movie_ver2.theater.entity.Theater;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TheaterInfoDto {
    private String area;
    private String address;
    private String number;

    @Builder
    public TheaterInfoDto(String area, String address, String number) {
        this.area = area;
        this.address = address;
        this.number = number;
    }

    public static TheaterInfoDto of(Theater theater) {
        return TheaterInfoDto.builder()
                .area(theater.getArea())
                .address(theater.getAddress())
                .number(theater.getNumber())
                .build();
    }
}
