package com.example.movie_ver2.theater.dto;

import com.example.movie_ver2.theater.entity.Theater;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TheaterAreaDto {
    private Long id;
    private String area;

    @Builder
    public TheaterAreaDto(Long id, String area) {
        this.id = id;
        this.area = area;
    }

    public static TheaterAreaDto of(Theater theater) {
        return TheaterAreaDto.builder()
                .id(theater.getId())
                .area(theater.getArea())
                .build();
    }
}
