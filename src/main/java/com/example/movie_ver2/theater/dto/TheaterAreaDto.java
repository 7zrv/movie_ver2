package com.example.movie_ver2.theater.dto;

import com.example.movie_ver2.screenMovie.entity.ScreenMovies;
import com.example.movie_ver2.theater.entity.Theater;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class TheaterAreaDto {
    private Long id;
    private String local;
    private String area;
    private List<Long> screenMovies;

    @Builder
    public TheaterAreaDto(Long id, String local, String area, List<Long> screenMovies) {
        this.id = id;
        this.local = local;
        this.area = area;
        this.screenMovies = screenMovies;
    }

    public static TheaterAreaDto of(Theater theater) {
        return TheaterAreaDto.builder()
                .id(theater.getId())
                .local(theater.getAddress().substring(0,2))
                .area(theater.getArea())
                .screenMovies(theater.getScreenMovies().stream().map(ScreenMovies::getId).collect(Collectors.toList()))
                .build();
    }
}
