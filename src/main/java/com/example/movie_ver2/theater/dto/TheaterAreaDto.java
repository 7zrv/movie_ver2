package com.example.movie_ver2.theater.dto;

import com.example.movie_ver2.theater.entity.Theater;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TheaterAreaDto {
    private Long id;
    private String local;
    private String area;

    @Builder
    public TheaterAreaDto(Long id, String local, String area) {
        this.id = id;
        this.local = local;
        this.area = area;
    }

    public static TheaterAreaDto of(Theater theater) {
        return TheaterAreaDto.builder()
                .id(theater.getId())
                .local(theater.getAddress().substring(0,2))
                .area(theater.getArea())
                .build();
    }
}
