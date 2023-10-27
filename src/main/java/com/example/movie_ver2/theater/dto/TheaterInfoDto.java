package com.example.movie_ver2.theater.dto;

import com.example.movie_ver2.theater.entity.Theater;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TheaterInfoDto {
    private Long id;
    private String area;
    private String address;
    private String number;
    private Long halls;

    @Builder
    public TheaterInfoDto(Long id, String area, String address, String number, Long halls) {
        this.id = id;
        this.area = area;
        this.address = address;
        this.number = number;
        this.halls = halls;    //보유 관수
    }

    public static TheaterInfoDto of(Theater theater, Long countHalls) {
        return TheaterInfoDto.builder()
                .id(theater.getId())
                .area(theater.getArea())
                .address(theater.getAddress())
                .number(theater.getNumber())
                .halls(countHalls)
                .build();
    }
}
