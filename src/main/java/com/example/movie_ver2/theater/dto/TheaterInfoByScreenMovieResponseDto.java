package com.example.movie_ver2.theater.dto;


import lombok.Getter;

import com.example.movie_ver2.theater.entity.Theater;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class TheaterInfoByScreenMovieResponseDto {
    private Long id;
    private String area;
    private String address;

    @Builder
    public TheaterInfoByScreenMovieResponseDto(Long id, String area, String address) {
        this.id = id;
        this.area = area;
        this.address = address.substring(0, 2);;

    }

    public static TheaterInfoByScreenMovieResponseDto of(Theater theater) {
        return TheaterInfoByScreenMovieResponseDto.builder()
                .id(theater.getId())
                .area(theater.getArea())
                .address(theater.getAddress())
                .build();
    }
}
